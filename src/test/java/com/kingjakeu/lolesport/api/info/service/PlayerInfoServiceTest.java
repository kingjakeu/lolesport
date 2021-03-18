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
}