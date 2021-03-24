package com.kingjakeu.lolesport.api.game.dao;

import com.kingjakeu.lolesport.api.game.domain.Game;
import com.kingjakeu.lolesport.api.game.domain.TeamGameSummary;
import com.kingjakeu.lolesport.api.game.domain.TeamGameSummaryId;
import com.kingjakeu.lolesport.api.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamGameSummaryRepository extends JpaRepository<TeamGameSummary, TeamGameSummaryId> {
    int countAllByGameInAndTeamAndWin(List<Game> gameList, Team team, boolean win);
    int countAllByGameIn(List<Game> gameList);
}
