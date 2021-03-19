package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.info.dao.*;
import com.kingjakeu.lolesport.api.info.domain.Game;
import com.kingjakeu.lolesport.api.info.domain.League;
import com.kingjakeu.lolesport.api.info.domain.Match;
import com.kingjakeu.lolesport.api.info.domain.Tournament;
import com.kingjakeu.lolesport.api.info.dto.LolEsportDataDto;
import com.kingjakeu.lolesport.api.info.dto.game.GameDataDto;
import com.kingjakeu.lolesport.api.info.dto.game.GameEventDto;
import com.kingjakeu.lolesport.api.info.dto.league.LeagueDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleEventDto;
import com.kingjakeu.lolesport.api.info.dto.tournament.TournamentDataDto;
import com.kingjakeu.lolesport.api.info.dto.tournament.TournamentLeagueDto;
import com.kingjakeu.lolesport.common.constant.CommonCode;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchInfoService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final GameRepository gameRepository;

    // LEAGUE 저장
    public void crawlLeagueInfos() throws JsonProcessingException {
        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        LolEsportDataDto<LeagueDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.LEAGUE_LIST, parameters, new TypeReference<>() {});

        List<League> leagueList = resultDto.getData()
                .toLeagueEntities();

        this.leagueRepository.saveAll(leagueList);
    }

    // TOURNAMENT 저장
    public void crawlLeagueTournamentInfos(String leagueId) throws JsonProcessingException {
        final League league = this.findLeagueById(leagueId);

        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        parameters.put("leagueId", league.getId());

        LolEsportDataDto<TournamentDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.TOURNAMENT_LIST, parameters, new TypeReference<>() {});

        TournamentLeagueDto tournamentLeagueDto = resultDto.getData().getLeagues().get(0);

        this.tournamentRepository.saveAll(tournamentLeagueDto.toTournamentEntities(league));
    }

    // MATCH 저장
    public void crawlLeagueSchedules(String leagueId) throws JsonProcessingException {
        final League league = this.findLeagueById(leagueId);

        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        parameters.put("leagueId", league.getId());
        ScheduleDto scheduleDto = this.crawlLeagueSchedule(parameters);

        for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
            this.saveMatch(eventDto, league);
        }

        while (scheduleDto.getPages().getOlder() != null) {
            parameters.put("pageToken", scheduleDto.getPages().getOlder());
            scheduleDto = this.crawlLeagueSchedule(parameters);

            for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
                this.saveMatch(eventDto, league);
            }
        }
    }

    private void saveMatch(ScheduleEventDto eventDto, League league){
        if(eventDto.isNotInProgress()){
            Match match = eventDto.toMatchEntity(league);
            match.setTeam1(this.teamRepository.findByCode(match.getTeam1().getCode()));
            match.setTeam2(this.teamRepository.findByCode(match.getTeam2().getCode()));
            this.matchRepository.save(match);
        }
    }

    private League findLeagueById(String leagueId){
        Optional<League> leagueOptional = this.leagueRepository.findById(leagueId);
        if (leagueOptional.isEmpty()) return null;
        return leagueOptional.get();
    }

    // 스케줄
    private ScheduleDto crawlLeagueSchedule(Map<String, String> parameters) throws JsonProcessingException {
        LolEsportDataDto<ScheduleDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.LEAGUE_SCHEDULE_LIST, parameters, new TypeReference<>() {});
        return resultDto.getData()
                .getSchedule();
    }

    public void crawlTournamentMatchGameEvents(String tournamentId) throws Exception {
        Optional<Tournament> optionalTournament = this.tournamentRepository.findById(tournamentId);
        if(optionalTournament.isEmpty()) throw new Exception("ERROR");
        final Tournament tournament = optionalTournament.get();

        List<Match> matchList = this.matchRepository.findAllByStartTimeBetween(
                LocalDateTime.of(tournament.getStartDate(), LocalTime.MIN), LocalDateTime.of(tournament.getEndDate(), LocalTime.MIN));
        for(Match match : matchList){
            this.crawlMatchGameEvent(match.getId());
        }

    }

    // GAME 저장
    public void crawlMatchGameEvents() throws JsonProcessingException {
        List<Match> matchList = this.matchRepository.findAll();
        for(Match match : matchList){
            this.crawlMatchGameEvent(match.getId());
        }
    }

    public void crawlMatchGameEvent(String matchId) throws JsonProcessingException {
        Map<String, String> parameters =Crawler.createCommonLolEsportParameters();
        parameters.put("id", matchId);

        LolEsportDataDto<GameDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.MATCH_EVENT_DETAIL, parameters, new TypeReference<>() {});

        GameEventDto eventDto = resultDto.getData().getEvent();

        this.gameRepository.saveAll(eventDto.toGameEntities());
    }

    public void crawlAllLckMatchHistoryLink() throws Exception{
        List<Game> gameList = this.gameRepository.findAllByState(CommonCode.STATE_COMPLETED.getCode());
        Document document = Crawler.doGetDocument(CrawlUrl.LCK_MATCH_HISTORY_LIST.getUrl());

        for(Game game : gameList){
            this.crawlMatchHistoryLink(game, document);
        }
    }

    public void crawlLckMatchHistoryLink(String gameId) throws Exception {
        Optional<Game> optionalGame = this.gameRepository.findByIdAndState(gameId, CommonCode.STATE_COMPLETED.getCode());
        if(optionalGame.isEmpty()) throw new Exception("ERROR");

        final Game game = optionalGame.get();

        Document document = Crawler.doGetDocument(CrawlUrl.LCK_MATCH_HISTORY_LIST.getUrl());
        this.crawlMatchHistoryLink(game, document);
    }

    public void crawlMatchHistoryLink(Game game, Document document){
        Elements rowElements = document.getElementsByClass("mhgame-red multirow-highlighter");
        rowElements.addAll(document.getElementsByClass("mhgame-blue multirow-highlighter"));

        LinkedList<String> linkList = new LinkedList<>();
        for(Element row : rowElements){
            String gameDate = row.getElementsByClass("mhgame-result").get(0).text();

            Elements cells = row.getElementsByTag("a");
            String blueTeamLink = cells.get(1).attr("href");
            String redTeamLink = cells.get(2).attr("href");

            String matchHistoryLink = cells.get(15).attr("href");
            if(game.getMatch().startDateEqualsTo(LocalDate.parse(gameDate))){
                if((game.getBlueTeam().crawlKeyEqualsTo(blueTeamLink) && game.getRedTeam().crawlKeyEqualsTo(redTeamLink))
                        || (game.getBlueTeam().crawlKeyEqualsTo(redTeamLink) && game.getRedTeam().crawlKeyEqualsTo(blueTeamLink))) {
                    linkList.addFirst(matchHistoryLink);
                }
            }
        }
        if(!linkList.isEmpty()){
            game.setMatchHistoryUrl(linkList.get((int) (game.getNumber()-1)));
            this.gameRepository.save(game);
        }
    }
}
