package com.kingjakeu.promode.api.team.dao;

import com.kingjakeu.promode.api.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByCode(String code);
    Optional<Team> findByName(String name);
    List<Team> findAllByLeagueId(String leagueId);
}
