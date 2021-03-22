package com.kingjakeu.lolesport.api.champion.dao;

import com.kingjakeu.lolesport.api.champion.domain.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChampionRepository extends JpaRepository<Champion, String> {
    Optional<Champion> findByCrawlKey(String crawlKey);
}
