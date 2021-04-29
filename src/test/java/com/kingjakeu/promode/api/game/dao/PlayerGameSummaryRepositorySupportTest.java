package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.dto.PlayerStatDto;
import com.kingjakeu.promode.common.constant.LolRole;
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
class PlayerGameSummaryRepositorySupportTest {

    @Autowired
    private PlayerGameSummaryRepositorySupport playerGameSummaryRepositorySupport;

    @Test
    void findBestPlayerStatDto() {
        List<PlayerStatDto> playerStatDto = this.playerGameSummaryRepositorySupport.findPlayerStatDtoList(
                "105522984810490982", "WEEK9", LolRole.TOP
        );
        System.out.println(playerStatDto.toString());
    }
}