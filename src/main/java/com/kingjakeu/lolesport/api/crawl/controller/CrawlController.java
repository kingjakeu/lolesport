package com.kingjakeu.lolesport.api.crawl.controller;

import com.kingjakeu.lolesport.api.crawl.service.MatchInfoCrawlService;
import com.kingjakeu.lolesport.api.crawl.service.PlayerInfoCrawlService;
import com.kingjakeu.lolesport.api.crawl.service.TeamInfoCrawlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/crawl")
@RequiredArgsConstructor
public class CrawlController {

    private final MatchInfoCrawlService matchInfoCrawlService;
    private final TeamInfoCrawlService teamInfoCrawlService;
    private final PlayerInfoCrawlService playerInfoCrawlService;

    @PostMapping(value = "/league")
    public void crawlAllLeagueInfo(){
        this.matchInfoCrawlService.crawlLeagueInfos();
    }

    @PostMapping(value = "/tournament")
    public void crawlTournamentInfo(@RequestBody String leagueId){
        this.matchInfoCrawlService.crawlLeagueTournamentInfos(leagueId);
    }

    @PostMapping(value = "/match")
    public void crawlMatchInfos(@RequestBody String tournamentId){
        this.matchInfoCrawlService.crawlLeagueMatchSchedules(tournamentId);
    }

    @PostMapping(value = "/game")
    public void crawlMatchGameEvents(@RequestBody Optional<String> matchId){
        if(matchId.isEmpty()) {
            this.matchInfoCrawlService.crawlMatchGameEvents();
        }else {
            this.matchInfoCrawlService.crawlMatchGameEvents(matchId.get());
        }
    }

    @PostMapping(value = "/match-history-link/lck")
    public void crawlGameMatchHistoryLink(@RequestBody Optional<String> gameId){
        if(gameId.isEmpty()){
            this.matchInfoCrawlService.crawlAllLckMatchHistoryLink();
        }else{
            this.matchInfoCrawlService.crawlLckMatchHistoryLink(gameId.get());
        }
    }

    @PostMapping(value = "team-player")
    public void crawlTeamAndPlayer(@RequestBody String leagueName){
        this.teamInfoCrawlService.crawlTeamsAndPlayers(leagueName);
    }

    @PostMapping(value = "team/detail/lck")
    public void crawlLckTeamsDetail(){
        this.teamInfoCrawlService.crawlLckTeamsDetail();
    }

    @PostMapping(value = "player/detail/lck")
    public void crawlLckPlayerDetail(){
        this.playerInfoCrawlService.crawlLckPlayersDetail();
    }
}
