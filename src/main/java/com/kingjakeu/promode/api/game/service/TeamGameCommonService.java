package com.kingjakeu.promode.api.game.service;

import com.kingjakeu.promode.api.game.dao.TeamGameSummaryRepository;
import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.common.constant.CommonError;
import com.kingjakeu.promode.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<TeamGameSummary> findTeamGameSummaryByGame(Game game){
        return this.teamGameSummaryRepository.findAllByGame(game);
    }

    public TeamGameSummary findTeamGameSummaryByGameIdAndSide(String gameId, String side){
        Optional<TeamGameSummary> optionalTeamGameSummary = this.teamGameSummaryRepository
                .findTeamGameSummaryByGameIdAndSide(gameId, side);
        if(optionalTeamGameSummary.isEmpty()) throw new ResourceNotFoundException(CommonError.GAME_INFO_NOT_FOUND);
        return optionalTeamGameSummary.get();
    }

}
