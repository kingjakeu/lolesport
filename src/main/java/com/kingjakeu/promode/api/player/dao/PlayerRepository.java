package com.kingjakeu.promode.api.player.dao;

import com.kingjakeu.promode.api.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findBySummonerName(String summonerName);
}
