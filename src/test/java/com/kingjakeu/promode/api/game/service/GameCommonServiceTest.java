package com.kingjakeu.promode.api.game.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameCommonServiceTest {

    @Autowired
    private GameCommonService gameCommonService;
    @Test
    void findPlayerAverageSummaryList() {
    }
}