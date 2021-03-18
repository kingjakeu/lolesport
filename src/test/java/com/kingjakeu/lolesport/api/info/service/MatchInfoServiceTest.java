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
class MatchInfoServiceTest {

    @Autowired
    private TeamInfoService teamInfoService;

    @Autowired
    private MatchInfoService matchInfoService;

    @Test
    @Order(1)
    void crawlLeagueInfos() {
        try{
            this.matchInfoService.crawlLeagueInfos();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void crawlLeagueTournamentInfos() {
        try {
            this.matchInfoService.crawlLeagueTournamentInfos("98767991310872058");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void crawlLeagueSchedule(){
        try {
            this.teamInfoService.crawlTeams("LCK");
            this.matchInfoService.crawlLeagueSchedules("98767991310872058");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    //@Test
   // @Order(4)
    void testCrawlMatchEvents() {
        try{
            this.matchInfoService.crawlMatchEvents();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    //@Test
    void crawlMatchEvents() {
        try {
            this.matchInfoService.crawlMatchEvent("105522984812916145");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}