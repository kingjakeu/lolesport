package com.kingjakeu.promode.api.ban.service;

import com.kingjakeu.promode.api.ban.dto.response.TournamentTeamChampBanResDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class BanHistoryServiceTest {

    @Autowired
    BanHistoryService banHistoryService;

    @Test
    void findMostBanByTeamInTournament() {
        TournamentTeamChampBanResDto result = this.banHistoryService.findMostBanByTeamInTournament(
                "105522984810490982",
                "99566404585387054"
        );

        System.out.println(result.toString());
    }
}