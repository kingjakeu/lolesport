package com.kingjakeu.lolesport.api.team.dao;

import com.kingjakeu.lolesport.api.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByCode(String code);
    Optional<Team> findByName(String name);
}
