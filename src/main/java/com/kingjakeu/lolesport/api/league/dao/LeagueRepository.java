package com.kingjakeu.lolesport.api.league.dao;

import com.kingjakeu.lolesport.api.league.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, String> {
    Optional<League> findByName(String name);
}
