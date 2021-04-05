package com.kingjakeu.promode.api.standing.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class StandingCommonServiceTest {

    @Autowired
    StandingCommonService standingCommonService;

    @Test
    void calculateStanding() {
        this.standingCommonService.calculateStanding("105522984810490982");
    }
}