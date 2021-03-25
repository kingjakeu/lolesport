package com.kingjakeu.lolesport.api.pick.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PickHistoryInfoServiceTest {

    @Autowired
    PickHistoryInfoService pickHistoryInfoService;

    @Test
    void findMostPickByTeamInTournament() {
        System.out.println( this.pickHistoryInfoService.findMostPickByTeamInTournament(
                "105522984810490982",
                "99566404585387054").toString()
        );
    }
}