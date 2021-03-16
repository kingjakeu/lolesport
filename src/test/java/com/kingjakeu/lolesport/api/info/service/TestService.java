package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestService {

    @Autowired
    private TeamInfoService teamInfoService;

    @Autowired
    private PlayerInfoService playerInfoService;

    @Test
    public void createTeamTest(){
        this.teamInfoService.createTeam();
    }

    @Test
    public void crawlLckTeamsTest(){
        this.teamInfoService.crawlLckTeams();
    }

    @Test
    public void crawlLckPlayer(){
        this.playerInfoService.crawlLckPlayers();
    }

    @Test
    public void crawlPlayer(){
        this.playerInfoService.crawlPlayer("/Kiin");
    }

    @Test
    public void newCrawlTest(){
        try {
            this.teamInfoService.newCrawl();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
