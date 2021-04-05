package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.match.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, String> {
    Optional<Game> findByIdAndState(String id, String state);
    List<Game> findAllByState(String state);
    List<Game> findAllByMatchId(String matchId);
    List<Game> findAllByMatchAndState(Match match, String state);
}
