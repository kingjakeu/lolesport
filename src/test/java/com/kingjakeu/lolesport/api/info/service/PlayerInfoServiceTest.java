package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlayerInfoServiceTest {

    @Autowired
    private PlayerInfoService playerInfoService;

    @Test
    void crawlPlayersByTeam() {
        try {
            this.playerInfoService.crawlPlayersByTeam("100725845018863243");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void crawlLckPlayersCrawlKeyword() {
        try {
            this.playerInfoService.crawlLckPlayersCrawlKeyword();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void crawlPlayersDetailsInfo() {
        try {
            this.playerInfoService.crawlPlayersDetailsInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}