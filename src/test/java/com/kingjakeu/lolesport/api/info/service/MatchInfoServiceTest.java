package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchInfoServiceTest {

    @Autowired
    private MatchInfoService matchInfoService;

    @Test
    public void crawlLeagueSchedule(){
        try {
            this.matchInfoService.crawlLeagueSchedules("98767991310872058");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void crawlMatchEvents() {
        try {
            this.matchInfoService.crawlMatchEvent("105522984812916145");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCrawlMatchEvents() {
        try{
            this.matchInfoService.crawlMatchEvents();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void crawlLeagueInfos() {
        try{
            this.matchInfoService.crawlLeagueInfos();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @Test
    void crawlLeagueTournamentInfos() {
        try {
            this.matchInfoService.crawlLeagueTournamentInfos("98767991310872058");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}