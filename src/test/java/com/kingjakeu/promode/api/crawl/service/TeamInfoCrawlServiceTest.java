package com.kingjakeu.promode.api.crawl.service;

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
class TeamInfoCrawlServiceTest {

    @Autowired
    private TeamInfoCrawlService teamInfoCrawlService;

    @Autowired
    private MatchInfoCrawlService matchInfoCrawlService;

    @Order(1)
    @Test
    void crawlTeams() {
        this.matchInfoCrawlService.crawlLeagueInfos();
        this.teamInfoCrawlService.crawlTeamsAndPlayers("LCK");
    }

    @Order(2)
    @Test
    void crawlLckTeams() {
        this.teamInfoCrawlService.crawlLckTeamsDetail();
    }
}