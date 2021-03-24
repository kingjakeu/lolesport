package com.kingjakeu.lolesport.api.standing.dao;

import com.kingjakeu.lolesport.api.standing.domain.Standing;
import com.kingjakeu.lolesport.api.standing.domain.StandingId;
import com.kingjakeu.lolesport.api.tournament.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandingRepository extends JpaRepository<Standing, StandingId> {
    List<Standing> findAllByTournamentOrderByRankAsc(Tournament tournament);
}
