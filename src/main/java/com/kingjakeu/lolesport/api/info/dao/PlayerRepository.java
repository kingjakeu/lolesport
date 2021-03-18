package com.kingjakeu.lolesport.api.info.dao;

import com.kingjakeu.lolesport.api.info.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findBySummonerName(String summonerName);
}
