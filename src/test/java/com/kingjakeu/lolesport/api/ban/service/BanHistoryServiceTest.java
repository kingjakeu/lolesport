package com.kingjakeu.lolesport.api.ban.service;

import com.kingjakeu.lolesport.api.ban.dto.ChampBanInfoDto;
import com.kingjakeu.lolesport.api.ban.dto.response.TournamentTeamChampBanResDto;
import com.kingjakeu.lolesport.api.champion.domain.Champion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
    }
}