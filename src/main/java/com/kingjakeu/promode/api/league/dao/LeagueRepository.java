package com.kingjakeu.promode.api.league.dao;

import com.kingjakeu.promode.api.league.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, String> {
    Optional<League> findByName(String name);
}
