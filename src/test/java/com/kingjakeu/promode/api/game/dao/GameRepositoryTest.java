package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.Game;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    void findByIdAndState() {

    }

    @Test
    void findAllByState() {
        List<Game> gameList = this.gameRepository.findAllByMatchId("105522984812719209");
    }

    @Test
    void findAllByMatchId() {
    }

    @Test
    void findAllByMatchIdAndState() {
    }
}