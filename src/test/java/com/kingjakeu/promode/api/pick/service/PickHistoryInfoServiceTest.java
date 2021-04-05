package com.kingjakeu.promode.api.pick.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

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

    @Test
    void test(){
        BigDecimal s = new BigDecimal("-12.32");
        System.out.println(s.abs().toPlainString());
    }
}