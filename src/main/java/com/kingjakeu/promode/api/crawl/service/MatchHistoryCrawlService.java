package com.kingjakeu.promode.api.crawl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.promode.api.ban.domain.BanHistory;
import com.kingjakeu.promode.api.ban.domain.BanHistoryId;
import com.kingjakeu.promode.api.champion.domain.Champion;
import com.kingjakeu.promode.api.champion.service.ChampionCommonService;
import com.kingjakeu.promode.api.ban.dao.BanHistoryRepository;
import com.kingjakeu.promode.api.config.service.ConfigService;
import com.kingjakeu.promode.api.crawl.dto.matchhistory.ParticipantDto;
import com.kingjakeu.promode.api.crawl.dto.request.MatchHistoryRequestDto;
import com.kingjakeu.promode.api.game.dao.GameRepository;
import com.kingjakeu.promode.api.crawl.dto.matchhistory.MatchHistoryDto;
import com.kingjakeu.promode.api.crawl.dto.matchhistory.TeamDto;
import com.kingjakeu.promode.api.game.dao.PlayerGameSummaryRepository;
import com.kingjakeu.promode.api.game.dao.TeamGameSummaryRepository;
import com.kingjakeu.promode.api.game.domain.*;
import com.kingjakeu.promode.api.game.service.GameCommonService;
import com.kingjakeu.promode.api.pick.dao.PickHistoryRepository;
import com.kingjakeu.promode.api.pick.domain.PickHistory;
import com.kingjakeu.promode.api.pick.domain.PickHistoryId;
import com.kingjakeu.promode.api.player.domain.Player;
import com.kingjakeu.promode.api.player.service.PlayerCommonService;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.common.constant.CommonCode;
import com.kingjakeu.promode.common.constant.CommonError;
import com.kingjakeu.promode.common.constant.LolRole;
import com.kingjakeu.promode.common.exception.ResourceNotFoundException;
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
    private final GameRepository gameRepository;
    private final ChampionCommonService championCommonService;
    private final PlayerCommonService playerCommonService;
    private final PlayerGameSummaryRepository playerGameSummaryRepository;
    private final TeamGameSummaryRepository teamGameSummaryRepository;


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

    public void crawlGameMatchHistory(List<Game> gameList) {
        for(Game game : gameList){
            this.crawlGameMatchHistory(game);
        }
    }

    public void crawlGameMatchHistory(Game game) {
        if(game.isMatchHistoryLinkEmpty()) throw new ResourceNotFoundException(CommonError.GAME_MATCH_INFO_NOT_FOUND);

        String matchHistoryPageBaseUrl = this.configService.findConfigValue("MATCH_HISTORY_PAGE_BASE");
        String matchHistoryApiBaseUrl = this.configService.findConfigValue("MATCH_HISTORY_API_BASE");
        String url = game.getMatchHistoryUrl().replace(matchHistoryPageBaseUrl, matchHistoryApiBaseUrl);
        MatchHistoryDto matchHistoryDto = crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});

        if (game.getPatchVersion() == null){
            game.setPatchVersion(matchHistoryDto.getGameVersion());
            this.gameRepository.save(game);
        }
        this.saveTeamGameSummary(game, matchHistoryDto);
        this.refineBanLog(game, matchHistoryDto);
        this.saveParticipantData(game, matchHistoryDto);
    }

    public void saveTeamGameSummary(Game game, MatchHistoryDto matchHistoryDto){
        this.saveTeamGameSummary(game, matchHistoryDto, CommonCode.BLUE_SIDE.getCode());
        this.saveTeamGameSummary(game, matchHistoryDto, CommonCode.RED_SIDE.getCode());
    }

    private void saveTeamGameSummary(Game game, MatchHistoryDto matchHistoryDto, String side){
        Team team = CommonCode.BLUE_SIDE.codeEqualsTo(side) ? game.getBlueTeam() : game.getRedTeam();

        TeamGameSummaryId teamGameSummaryId = TeamGameSummaryId.builder()
                .gameId(game.getId())
                .teamId(team.getId())
                .build();

        TeamGameSummary teamGameSummary =  CommonCode.BLUE_SIDE.codeEqualsTo(side) ?
                matchHistoryDto.getBlueTeamDto().toTeamGameSummaryEntity()
                : matchHistoryDto.getRedTeamDto().toTeamGameSummaryEntity();

        teamGameSummary.setTeamGameSummaryId(teamGameSummaryId);
        teamGameSummary.setGame(game);
        teamGameSummary.setTeam(team);
        teamGameSummary.setSide(side);
        this.teamGameSummaryRepository.save(teamGameSummary);
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
            BanHistoryId banHistoryId = BanHistoryId.builder()
                    .gameId(game.getId())
                    .side(side)
                    .banTurn(banTurn++)
                    .build();

            BanHistory banHistory = BanHistory.builder()
                    .banHistoryId(banHistoryId)
                    .game(game)
                    .bannedChampion(this.championCommonService.findById(champKey))
                    .patchVersion(matchHistoryDto.getGameVersion())
                    .build();
            this.banHistoryRepository.save(banHistory);
        }
    }
    private void saveParticipantData(Game game, MatchHistoryDto matchHistoryDto){
        int i = 0;
        for(ParticipantDto participantDto : matchHistoryDto.getParticipants()){
            Player player = this.playerCommonService.findPlayerBySummonerName(
                    matchHistoryDto.findSummonerNameById(participantDto.getParticipantId()));
            Champion champion = this.championCommonService.findById(participantDto.getChampionId().toString());
            String side = i < 5 ? CommonCode.BLUE_SIDE.getCode() : CommonCode.RED_SIDE.getCode();
            LolRole lolRole = LolRole.findBySequence(i);

            PickHistoryId pickHistoryId = PickHistoryId.builder()
                    .gameId(game.getId())
                    .role(lolRole)
                    .side(side)
                    .build();

            PickHistory pickHistory = PickHistory.builder()
                    .pickHistoryId(pickHistoryId)
                    .game(game)
                    .player(player)
                    .champion(champion)
                    .patchVersion(matchHistoryDto.getGameVersion())
                    .build();
            this.pickHistoryRepository.save(pickHistory);

            PlayerGameSummaryId playerGameSummaryId = PlayerGameSummaryId.builder()
                    .gameId(game.getId())
                    .playerId(player.getId())
                    .build();

            PlayerGameSummary playerGameSummary = participantDto.getStats().toPlayerGameSummaryEntity();
            playerGameSummary.setPlayerGameSummaryId(playerGameSummaryId);
            playerGameSummary.setGame(game);
            playerGameSummary.setPlayer(player);
            playerGameSummary.setChampion(champion);
            playerGameSummary.setSide(side);
            playerGameSummary.setRole(lolRole);
            this.playerGameSummaryRepository.save(playerGameSummary);

            i += 1;
        }
    }
}
