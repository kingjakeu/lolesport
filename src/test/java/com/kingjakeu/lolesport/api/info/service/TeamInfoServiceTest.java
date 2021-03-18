package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TeamInfoServiceTest {
    @Autowired
    private TeamInfoService teamInfoService;

    @Autowired
    private MatchInfoService matchInfoService;

    @Test
    void crawlTeams() {
        try {
            this.matchInfoService.crawlLeagueInfos();
            this.teamInfoService.crawlTeams("LCK");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}