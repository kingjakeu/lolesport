package com.kingjakeu.lolesport.api.player.dao;

import com.kingjakeu.lolesport.api.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findBySummonerName(String summonerName);
}
