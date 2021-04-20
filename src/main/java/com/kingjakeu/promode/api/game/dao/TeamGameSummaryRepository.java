package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.kingjakeu.promode.api.game.domain.TeamGameSummaryId;
import com.kingjakeu.promode.api.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamGameSummaryRepository extends JpaRepository<TeamGameSummary, TeamGameSummaryId> {
    int countAllByGameInAndTeamAndWin(List<Game> gameList, Team team, boolean win);
    int countAllByGameIn(List<Game> gameList);
    List<TeamGameSummary> findAllByGame(Game game);
    Optional<TeamGameSummary> findTeamGameSummaryByGameIdAndSide(String gameId, String side);
}
