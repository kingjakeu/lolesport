package com.kingjakeu.promode.api.team.dao;

import com.kingjakeu.promode.api.team.domain.Team;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void findByCode() {
        Team team = this.teamRepository.findByCode("DWG");
    }

    @Test
    void findByName() {

    }

    @Test
    void findAllByLeagueId() {
    }
}