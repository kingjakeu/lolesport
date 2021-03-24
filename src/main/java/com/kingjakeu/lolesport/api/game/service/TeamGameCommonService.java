package com.kingjakeu.lolesport.api.game.service;

import com.kingjakeu.lolesport.api.game.dao.TeamGameSummaryRepository;
import com.kingjakeu.lolesport.api.game.domain.Game;
import com.kingjakeu.lolesport.api.team.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamGameCommonService {
    private final TeamGameSummaryRepository teamGameSummaryRepository;

    public boolean isGameListExisted(List<Game> gameList){
        return this.teamGameSummaryRepository.countAllByGameIn(gameList) > 0;
    }

    public int countTeamWinInGameList(Team team, List<Game> gameList){
        return this.teamGameSummaryRepository.countAllByGameInAndTeamAndWin(gameList, team, true);
    }
}
