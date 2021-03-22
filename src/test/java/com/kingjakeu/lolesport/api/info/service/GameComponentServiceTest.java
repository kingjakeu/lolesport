package com.kingjakeu.lolesport.api.info.service;

import com.kingjakeu.lolesport.api.info.dao.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameComponentServiceTest {

    @Autowired
    GameComponentService gameComponentService;

    @Autowired
    GameRepository gameRepository;
    @Test
    void crawlChampionData() {
        try {
            this.gameComponentService.crawlChampionData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
