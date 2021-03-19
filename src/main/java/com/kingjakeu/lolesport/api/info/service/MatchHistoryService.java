package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.config.dao.ConfigurationRepository;
import com.kingjakeu.lolesport.api.config.domain.Configuration;
import com.kingjakeu.lolesport.api.info.dao.BanHistoryRepository;
import com.kingjakeu.lolesport.api.info.dao.ChampionRepository;
import com.kingjakeu.lolesport.api.info.dao.GameRepository;
import com.kingjakeu.lolesport.api.info.domain.BanHistory;
import com.kingjakeu.lolesport.api.info.domain.Champion;
import com.kingjakeu.lolesport.api.info.domain.Game;
import com.kingjakeu.lolesport.api.info.domain.Team;
import com.kingjakeu.lolesport.api.info.dto.matchHistory.MatchHistoryDto;
import com.kingjakeu.lolesport.api.info.dto.matchHistory.TeamDto;
import com.kingjakeu.lolesport.api.info.dto.timeline.TimeLineDto;
import com.kingjakeu.lolesport.common.constant.CommonCode;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchHistoryService {

    private final CrawlCommonService crawlCommonService;
    private final GameRepository gameRepository;
    private final ChampionRepository championRepository;
    private final BanHistoryRepository banHistoryRepository;
    private final ConfigurationRepository configurationRepository;


    public void crawlGameTimeLine(String url) throws JsonProcessingException{
        TimeLineDto timeLineDto = Crawler.doGetObject(url, CrawlUrl.acsMatchHistoryHeader(), Collections.emptyMap(), new TypeReference<TimeLineDto>() {});
        System.out.println("DONE");
    }

    public void crawlGameMatchHistory(String gameId) throws Exception {
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if(optionalGame.isEmpty()) throw new Exception("ERROR");
        final Game game = optionalGame.get();

        if(game.isMatchHistoryLinkEmpty()) throw new Exception("ERROR");

        String matchHistoryPageBaseUrl = this.findConfigValue("MATCH_HISTORY_PAGE_BASE");
        String matchHistoryApiBaseUrl = this.findConfigValue("MATCH_HISTORY_API_BASE");
        String url = game.getMatchHistoryUrl().replace(matchHistoryPageBaseUrl, matchHistoryApiBaseUrl);
        MatchHistoryDto matchHistoryDto = crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});

        this.refineBanLog(game, matchHistoryDto);
    }

    public void refineBanLog(Game game, MatchHistoryDto matchHistoryDto) throws Exception {
        this.saveBanHistory(game, matchHistoryDto, CommonCode.BLUE_SIDE.getCode());
        this.saveBanHistory(game, matchHistoryDto, CommonCode.RED_SIDE.getCode());
    }

    private void saveBanHistory(Game game, MatchHistoryDto matchHistoryDto, String side) throws Exception {
        TeamDto teamDto = CommonCode.BLUE_SIDE.codeEqualsTo(side) ? matchHistoryDto.getBlueTeamDto() : matchHistoryDto.getRedTeamDto();
        List<String> banChampionKeyList = teamDto.getBanChampionKeyList();

        int banTurn = 1;
        for(String champKey : banChampionKeyList){
            BanHistory banHistory = BanHistory.builder()
                    .game(game)
                    .banPickTeam(CommonCode.BLUE_SIDE.codeEqualsTo(side) ? game.getBlueTeam() : game.getRedTeam())
                    .oppositeTeam(CommonCode.BLUE_SIDE.codeEqualsTo(side) ? game.getRedTeam() : game.getBlueTeam())
                    .bannedChampion(this.findByCrawlKey(champKey))
                    .side(side)
                    .banTurn(banTurn++)
                    .patchVersion(matchHistoryDto.getGameVersion())
                    .build();
            this.banHistoryRepository.save(banHistory);
        }
    }

    private Champion findByCrawlKey(String crawlKey) throws Exception {
        Optional<Champion> optionalChampion = this.championRepository.findByCrawlKey(crawlKey);
        if(optionalChampion.isEmpty()) throw new Exception("ERROR");
        return optionalChampion.get();
    }

    private String findConfigValue(String configKey) throws Exception {
        Optional<Configuration> configurationOptional = this.configurationRepository.findById(configKey);
        if(configurationOptional.isEmpty()) throw new Exception("ERROR");
        return configurationOptional.get().getValue();
    }
}
