package com.kingjakeu.promode.api.pick.service;

import com.kingjakeu.promode.api.pick.dao.PickHistoryRepositorySupport;
import com.kingjakeu.promode.api.pick.dto.ChampPickInfoDto;
import com.kingjakeu.promode.common.constant.LolRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PickHistoryInfoServiceTest {

    @Autowired
    PickHistoryInfoService pickHistoryInfoService;

    @Autowired
    PickHistoryRepositorySupport pickHistoryRepositorySupport;

    @Test
    void findMostPickByTeamInTournament() {
        System.out.println( this.pickHistoryInfoService.findMostPickByTeamInTournament(
                "105522984810490982",
                "99566404585387054").toString()
        );
    }

    @Test
    void test(){
        Map<String, ChampPickInfoDto>  result = this.pickHistoryInfoService.getBestPickOfTheWeek(LocalDate.parse("2021-03-19"));
        System.out.println(result);
    }
}