package com.kingjakeu.promode.api.player.service;

import com.kingjakeu.promode.api.game.dto.PlayerStatDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    void getBestPlayerSummaryList() {
        List<PlayerStatDto> playerStatDtoList = this.playerService.getBestPlayerStatList("105522984810490982", "WEEK9");
        System.out.println(playerStatDtoList.toString());
    }
}