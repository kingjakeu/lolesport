package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
class TeamInfoServiceTest {

    @Autowired
    private TeamInfoService teamInfoService;

    @Autowired
    private MatchInfoService matchInfoService;

    @Order(1)
    @Test
    void crawlTeams() {
        try {
            this.matchInfoService.crawlLeagueInfos();
            this.teamInfoService.crawlTeamsAndPlayers("LCK");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Order(2)
    @Test
    void crawlLckTeams() {
        this.teamInfoService.crawlLckTeamsCrawlKeyword();
    }
}