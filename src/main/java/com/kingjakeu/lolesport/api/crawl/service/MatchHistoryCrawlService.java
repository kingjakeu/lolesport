package com.kingjakeu.lolesport.api.crawl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.ban.domain.BanHistory;
import com.kingjakeu.lolesport.api.ban.domain.BanHistoryId;
import com.kingjakeu.lolesport.api.champion.service.ChampionCommonService;
import com.kingjakeu.lolesport.api.config.dao.ConfigurationRepository;
import com.kingjakeu.lolesport.api.ban.dao.BanHistoryRepository;
import com.kingjakeu.lolesport.api.config.service.ConfigService;
import com.kingjakeu.lolesport.api.crawl.dto.matchhistory.ParticipantDto;
import com.kingjakeu.lolesport.api.crawl.dto.request.MatchHistoryRequestDto;
import com.kingjakeu.lolesport.api.game.dao.GameRepository;
import com.kingjakeu.lolesport.api.crawl.dto.matchhistory.MatchHistoryDto;
import com.kingjakeu.lolesport.api.crawl.dto.matchhistory.TeamDto;
import com.kingjakeu.lolesport.api.game.domain.Game;
import com.kingjakeu.lolesport.api.game.service.GameCommonService;
import com.kingjakeu.lolesport.api.pick.dao.PickHistoryRepository;
import com.kingjakeu.lolesport.api.pick.domain.PickHistory;
import com.kingjakeu.lolesport.api.pick.domain.PickHistoryId;
import com.kingjakeu.lolesport.api.player.service.PlayerCommonService;
import com.kingjakeu.lolesport.common.constant.CommonCode;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.constant.LolRole;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchHistoryCrawlService {

    private final CrawlCommonService crawlCommonService;
    private final BanHistoryRepository banHistoryRepository;
    private final PickHistoryRepository pickHistoryRepository;

    private final ConfigService configService;
    private final GameCommonService gameCommonService;
    private final ChampionCommonService championCommonService;
    private final PlayerCommonService playerCommonService;

    public void crawlGameTimeLine(String url) {
//        TimeLineDto timeLineDto = Crawler.doGetObject(url, CrawlUrl.acsMatchHistoryHeader(), Collections.emptyMap(), new TypeReference<TimeLineDto>() {});
//        System.out.println("DONE");
    }

    public void crawlGameMatchHistory(MatchHistoryRequestDto requestDto) {
        if (requestDto.getGameId() != null){
            final Game game = this.gameCommonService.findGameById(requestDto.getGameId());
            this.crawlGameMatchHistory(game);
        }else if(requestDto.getMatchId() != null){
            List<Game> gameList = this.gameCommonService.findGameByMatchId(requestDto.getMatchId());
            this.crawlGameMatchHistory(gameList);
        }else{
            throw new ResourceNotFoundException(CommonError.GAME_INFO_NOT_FOUND);
        }
    }
    private void crawlGameMatchHistory(List<Game> gameList) {
        for(Game game : gameList){
            this.crawlGameMatchHistory(game);
        }
    }

    private void crawlGameMatchHistory(Game game) {
        if(game.isMatchHistoryLinkEmpty()) throw new ResourceNotFoundException(CommonError.GAME_MATCH_INFO_NOT_FOUND);

        String matchHistoryPageBaseUrl = this.configService.findConfigValue("MATCH_HISTORY_PAGE_BASE");
        String matchHistoryApiBaseUrl = this.configService.findConfigValue("MATCH_HISTORY_API_BASE");
        String url = game.getMatchHistoryUrl().replace(matchHistoryPageBaseUrl, matchHistoryApiBaseUrl);
        MatchHistoryDto matchHistoryDto = crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});

        this.refineBanLog(game, matchHistoryDto);
        this.savePickHistory(game, matchHistoryDto);
    }

    public void refineBanLog(Game game, MatchHistoryDto matchHistoryDto) {
        this.saveBanHistory(game, matchHistoryDto, CommonCode.BLUE_SIDE.getCode());
        this.saveBanHistory(game, matchHistoryDto, CommonCode.RED_SIDE.getCode());
    }

    private void saveBanHistory(Game game, MatchHistoryDto matchHistoryDto, String side) {
        TeamDto teamDto = CommonCode.BLUE_SIDE.codeEqualsTo(side) ? matchHistoryDto.getBlueTeamDto() : matchHistoryDto.getRedTeamDto();
        List<String> banChampionKeyList = teamDto.getBanChampionKeyList();

        int banTurn = 1;
        for(String champKey : banChampionKeyList){
            BanHistoryId banHistoryId = BanHistoryId.builder().side(side).banTurn(banTurn++).build();
            BanHistory banHistory = BanHistory.builder()
                    .banHistoryId(banHistoryId)
                    .game(game)
                    .bannedChampion(this.championCommonService.findByCrawlKey(champKey))
                    .patchVersion(matchHistoryDto.getGameVersion())
                    .build();
            this.banHistoryRepository.save(banHistory);
        }
    }

    private void savePickHistory(Game game, MatchHistoryDto matchHistoryDto){
        int i = 0;
        for(ParticipantDto participantDto : matchHistoryDto.getParticipants()){
            PickHistoryId pickHistoryId = PickHistoryId.builder()
                    .role(LolRole.findBySequence(i))
                    .side(i < 5 ? CommonCode.BLUE_SIDE.getCode() : CommonCode.RED_SIDE.getCode())
                    .build();
            String summonerName = matchHistoryDto.findSummonerNameById(participantDto.getParticipantId());

            PickHistory pickHistory = PickHistory.builder()
                    .pickHistoryId(pickHistoryId)
                    .game(game)
                    .player(this.playerCommonService.findPlayerBySummonerName(summonerName))
                    .champion(this.championCommonService.findByCrawlKey(participantDto.getChampionId().toString()))
                    .patchVersion(matchHistoryDto.getGameVersion())
                    .build();
            this.pickHistoryRepository.save(pickHistory);
            i += 1;
        }
    }
}
