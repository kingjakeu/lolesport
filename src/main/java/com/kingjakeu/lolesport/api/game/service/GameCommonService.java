package com.kingjakeu.lolesport.api.game.service;

import com.kingjakeu.lolesport.api.game.dao.GameRepository;
import com.kingjakeu.lolesport.api.game.domain.Game;
import com.kingjakeu.lolesport.api.match.domain.Match;
import com.kingjakeu.lolesport.common.constant.CommonCode;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameCommonService {

    private final GameRepository gameRepository;

    public Game findGameById(String id){
        Optional<Game> gameOptional = this.gameRepository.findById(id);
        if(gameOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.GAME_INFO_NOT_FOUND);
        return gameOptional.get();
    }

    public List<Game> findGameByMatchId(String matchId){
       return this.gameRepository.findAllByMatchId(matchId);
    }

    public List<Game> findCompletedGameByMatch(Match match){
        return this.gameRepository.findAllByMatchAndState(match, CommonCode.STATE_COMPLETED.getCode());
    }
}
