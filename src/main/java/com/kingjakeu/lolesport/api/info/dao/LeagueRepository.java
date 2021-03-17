package com.kingjakeu.lolesport.api.info.dao;

import com.kingjakeu.lolesport.api.info.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, String> {
}
