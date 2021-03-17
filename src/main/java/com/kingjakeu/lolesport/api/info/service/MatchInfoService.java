package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.info.dao.GameRepository;
import com.kingjakeu.lolesport.api.info.dao.LeagueRepository;
import com.kingjakeu.lolesport.api.info.dao.MatchRepository;
import com.kingjakeu.lolesport.api.info.dao.TournamentRepository;
import com.kingjakeu.lolesport.api.info.domain.League;
import com.kingjakeu.lolesport.api.info.domain.Match;
import com.kingjakeu.lolesport.api.info.dto.LolEsportDataDto;
import com.kingjakeu.lolesport.api.info.dto.game.GameDataDto;
import com.kingjakeu.lolesport.api.info.dto.game.GameEventDto;
import com.kingjakeu.lolesport.api.info.dto.league.LeagueDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleEventDto;
import com.kingjakeu.lolesport.api.info.dto.tournament.TournamentDataDto;
import com.kingjakeu.lolesport.api.info.dto.tournament.TournamentLeagueDto;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchInfoService {

    private final LeagueRepository leagueRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final GameRepository gameRepository;


    public void crawlLeagueInfos() throws JsonProcessingException {
        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        LolEsportDataDto<LeagueDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.LEAGUE_LIST, parameters, new TypeReference<>() {});

        List<League> leagueList = resultDto.getData()
                .toLeagueEntities();

        this.leagueRepository.saveAll(leagueList);
    }

    public void crawlLeagueTournamentInfos(String leagueId) throws JsonProcessingException {
        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        parameters.put("leagueId", leagueId);

        LolEsportDataDto<TournamentDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.TOURNAMENT_LIST, parameters, new TypeReference<>() {});

        TournamentLeagueDto tournamentLeagueDto = resultDto.getData().getLeagues().get(0);

        this.tournamentRepository.saveAll(tournamentLeagueDto.toTournamentEntities());
    }

    private ScheduleDto crawlLeagueSchedule(Map<String, String> parameters) throws JsonProcessingException {
        LolEsportDataDto<ScheduleDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.LEAGUE_SCHEDULE_LIST, parameters, new TypeReference<>() {});

        return resultDto.getData()
                .getSchedule();
    }

    public void crawlLeagueSchedules(String leagueId) throws JsonProcessingException {
        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        parameters.put("leagueId", leagueId);
        ScheduleDto scheduleDto = this.crawlLeagueSchedule(parameters);

        List<Match> matches = new ArrayList<>();
        for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
            matches.add(eventDto.toMatchEntity());
        }

        while (scheduleDto.getPages().getOlder() != null) {
            parameters.put("pageToken", scheduleDto.getPages().getOlder());
            scheduleDto = this.crawlLeagueSchedule(parameters);
            for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
                matches.add(eventDto.toMatchEntity());
            }
        }
        this.matchRepository.saveAll(matches);
    }

    public void crawlMatchEvents() throws JsonProcessingException {
        List<Match> matchList = this.matchRepository.findAll();
        for(Match match : matchList){
            this.crawlMatchEvent(match.getId());
        }
    }

    public void crawlMatchEvent(String matchId) throws JsonProcessingException {
        Map<String, String> parameters =Crawler.createCommonLolEsportParameters();
        parameters.put("id", matchId);

        LolEsportDataDto<GameDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.MATCH_EVENT_DETAIL, parameters, new TypeReference<>() {});

        GameEventDto eventDto = resultDto.getData().getEvent();
        this.gameRepository.saveAll(eventDto.toGameEntities());
    }
}
