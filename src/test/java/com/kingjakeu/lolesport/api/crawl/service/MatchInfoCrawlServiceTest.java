package com.kingjakeu.lolesport.api.crawl.service;

import com.kingjakeu.lolesport.api.crawl.service.MatchInfoCrawlService;
import com.kingjakeu.lolesport.api.crawl.service.TeamInfoCrawlService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchInfoCrawlServiceTest {

    @Autowired
    private TeamInfoCrawlService teamInfoCrawlService;

    @Autowired
    private MatchInfoCrawlService matchInfoCrawlService;

    @Test
    @Order(1)
    void crawlLeagueInfos() {

        this.matchInfoCrawlService.crawlLeagueInfos();
    }

    @Test
    @Order(2)
    void crawlLeagueTournamentInfos() {
        this.matchInfoCrawlService.crawlLeagueTournamentInfos("98767991310872058");
    }

    @Test
    @Order(3)
    void crawlLeagueSchedule(){
        this.teamInfoCrawlService.crawlTeamsAndPlayers("LCK");
        this.matchInfoCrawlService.crawlLeagueMatchSchedules("98767991310872058");
    }

    @Test
    @Order(4)
    void testCrawlMatchEvents() {

        this.matchInfoCrawlService.crawlMatchGameEvents();
    }

    //@Test
    void crawlMatchEvents() {

        this.matchInfoCrawlService.crawlMatchGameEvents("105522984812916145");
    }

    @Test
    void crawlMatchHistoryLinks() {
        this.matchInfoCrawlService.crawlLckMatchHistoryLink("105522984812916176");
    }

    @Test
    void crawlAllMatchHistoryLinks() {
        this.matchInfoCrawlService.crawlAllLckMatchHistoryLink();
    }
}