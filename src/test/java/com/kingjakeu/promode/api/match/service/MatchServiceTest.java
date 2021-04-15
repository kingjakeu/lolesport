package com.kingjakeu.promode.api.match.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    void getMatch() {
        this.matchService.getMatch("20210325");
    }
}