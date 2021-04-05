package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.kingjakeu.promode.api.game.domain.TeamGameSummaryId;
import com.kingjakeu.promode.api.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamGameSummaryRepository extends JpaRepository<TeamGameSummary, TeamGameSummaryId> {
    int countAllByGameInAndTeamAndWin(List<Game> gameList, Team team, boolean win);
    int countAllByGameIn(List<Game> gameList);
}