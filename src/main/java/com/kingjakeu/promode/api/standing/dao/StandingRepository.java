package com.kingjakeu.promode.api.standing.dao;

import com.kingjakeu.promode.api.standing.domain.Standing;
import com.kingjakeu.promode.api.standing.domain.StandingId;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.api.tournament.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StandingRepository extends JpaRepository<Standing, StandingId> {
    List<Standing> findAllByTournamentOrderByRankAsc(Tournament tournament);
    Optional<Standing> findByTeam(Team team);
}
