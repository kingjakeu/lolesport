package com.kingjakeu.lolesport.api.crawl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.ban.domain.BanHistory;
import com.kingjakeu.lolesport.api.ban.domain.BanHistoryId;
import com.kingjakeu.lolesport.api.champion.domain.Champion;
import com.kingjakeu.lolesport.api.config.dao.ConfigurationRepository;
import com.kingjakeu.lolesport.api.config.domain.InternalConfig;
import com.kingjakeu.lolesport.api.ban.dao.BanHistoryRepository;
import com.kingjakeu.lolesport.api.champion.dao.ChampionRepository;
import com.kingjakeu.lolesport.api.game.dao.GameRepository;
import com.kingjakeu.lolesport.api.crawl.dto.matchHistory.MatchHistoryDto;
import com.kingjakeu.lolesport.api.crawl.dto.matchHistory.TeamDto;
import com.kingjakeu.lolesport.api.game.domain.Game;
import com.kingjakeu.lolesport.common.constant.CommonCode;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchHistoryCrawlService {

    private final CrawlCommonService crawlCommonService;
    private final GameRepository gameRepository;
    private final ChampionRepository championRepository;
    private final BanHistoryRepository banHistoryRepository;
    private final ConfigurationRepository configurationRepository;


    public void crawlGameTimeLine(String url) {
//        TimeLineDto timeLineDto = Crawler.doGetObject(url, CrawlUrl.acsMatchHistoryHeader(), Collections.emptyMap(), new TypeReference<TimeLineDto>() {});
//        System.out.println("DONE");
    }

    public void crawlGameMatchHistory(String gameId) {
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if(optionalGame.isEmpty()) throw new ResourceNotFoundException(CommonError.GAME_INFO_NOT_FOUND);
        final Game game = optionalGame.get();

        if(game.isMatchHistoryLinkEmpty()) throw new ResourceNotFoundException(CommonError.GAME_MATCH_INFO_NOT_FOUND);

        String matchHistoryPageBaseUrl = this.findConfigValue("MATCH_HISTORY_PAGE_BASE");
        String matchHistoryApiBaseUrl = this.findConfigValue("MATCH_HISTORY_API_BASE");
        String url = game.getMatchHistoryUrl().replace(matchHistoryPageBaseUrl, matchHistoryApiBaseUrl);
        MatchHistoryDto matchHistoryDto = crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});

        this.refineBanLog(game, matchHistoryDto);
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
                    .bannedChampion(this.findByCrawlKey(champKey))
                    .patchVersion(matchHistoryDto.getGameVersion())
                    .build();
            this.banHistoryRepository.save(banHistory);
        }
    }

    private Champion findByCrawlKey(String crawlKey) {
        Optional<Champion> optionalChampion = this.championRepository.findByCrawlKey(crawlKey);
        if(optionalChampion.isEmpty()) throw new ResourceNotFoundException(CommonError.CHAMP_NOT_FOUND);
        return optionalChampion.get();
    }

    private String findConfigValue(String configKey) {
        Optional<InternalConfig> configurationOptional = this.configurationRepository.findById(configKey);
        if(configurationOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.CONFIG_NOT_FOUND);
        return configurationOptional.get().getValue();
    }
}
