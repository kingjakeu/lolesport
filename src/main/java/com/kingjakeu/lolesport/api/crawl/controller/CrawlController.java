package com.kingjakeu.lolesport.api.crawl.controller;

import com.kingjakeu.lolesport.api.crawl.dto.request.MatchHistoryRequestDto;
import com.kingjakeu.lolesport.api.crawl.service.MatchHistoryCrawlService;
import com.kingjakeu.lolesport.api.crawl.service.MatchInfoCrawlService;
import com.kingjakeu.lolesport.api.crawl.service.PlayerInfoCrawlService;
import com.kingjakeu.lolesport.api.crawl.service.TeamInfoCrawlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/crawl")
@RequiredArgsConstructor
public class CrawlController {

    private final MatchInfoCrawlService matchInfoCrawlService;
    private final TeamInfoCrawlService teamInfoCrawlService;
    private final PlayerInfoCrawlService playerInfoCrawlService;
    private final MatchHistoryCrawlService matchHistoryCrawlService;

    @PostMapping(value = "/league")
    public void crawlAllLeagueInfo(){
        this.matchInfoCrawlService.crawlLeagueInfos();
    }

    @PostMapping(value = "/tournament")
    public void crawlTournamentInfo(@RequestParam String leagueId){
        this.matchInfoCrawlService.crawlLeagueTournamentInfos(leagueId);
    }

    @PostMapping(value = "/match")
    public void crawlMatchInfos(@RequestParam String tournamentId){
        this.matchInfoCrawlService.crawlLeagueMatchSchedules(tournamentId);
    }

    @PostMapping(value = "/game")
    public void crawlMatchGameEvents(@RequestParam Optional<String> matchId){
        if(matchId.isEmpty()) {
            this.matchInfoCrawlService.crawlMatchGameEvents();
        }else {
            this.matchInfoCrawlService.crawlMatchGameEvents(matchId.get());
        }
    }

    @PostMapping(value = "/match-history-link/lck")
    public void crawlGameMatchHistoryLink(@RequestParam Optional<String> gameId){
        if(gameId.isEmpty()){
            this.matchInfoCrawlService.crawlAllLckMatchHistoryLink();
        }else{
            this.matchInfoCrawlService.crawlLckMatchHistoryLink(gameId.get());
        }
    }

    @PostMapping(value = "/team-player")
    public void crawlTeamAndPlayer(@RequestParam String leagueName){
        this.teamInfoCrawlService.crawlTeamsAndPlayers(leagueName);
    }

    @PostMapping(value = "/team/detail/lck")
    public void crawlLckTeamsDetail(){
        this.teamInfoCrawlService.crawlLckTeamsDetail();
    }

    @PostMapping(value = "/player/detail/lck")
    public void crawlLckPlayerDetail(){
        this.playerInfoCrawlService.crawlLckPlayersDetail();
    }

    @PostMapping(value = "/match-history")
    public void crawlGameMatchHistoryInfo(@RequestBody MatchHistoryRequestDto requestDto){
        this.matchHistoryCrawlService.crawlGameMatchHistory(requestDto);
    }
}
