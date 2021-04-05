package com.kingjakeu.promode.api.champion.dao;

import com.kingjakeu.promode.api.champion.domain.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChampionRepository extends JpaRepository<Champion, String> {
    Optional<Champion> findByChampKey(String champKey);
}
