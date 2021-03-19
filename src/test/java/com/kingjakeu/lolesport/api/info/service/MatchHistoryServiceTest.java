package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchHistoryServiceTest {

    @Autowired
    private MatchHistoryService matchHistoryService;

    @Test
    void crawlMatchHistory() {
        try {
            matchHistoryService.crawlMatchHistory("https://acs.leagueoflegends.com/v1/stats/game/ESPORTSTMNT01/1885163?gameHash=21d6065b9600a6d9");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void crawlGameTimeLine() {
        try{
            matchHistoryService.crawlGameTimeLine("https://acs.leagueoflegends.com/v1/stats/game/ESPORTSTMNT01/1896701/timeline?gameHash=b5f757080fbc9aed");
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @Test
    void crawlGameMatchHistory() {
        try {
            this.matchHistoryService.crawlGameMatchHistory("105522984812916176");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}