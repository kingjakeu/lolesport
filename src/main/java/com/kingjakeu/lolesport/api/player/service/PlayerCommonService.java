package com.kingjakeu.lolesport.api.player.service;

import com.kingjakeu.lolesport.api.player.dao.PlayerRepository;
import com.kingjakeu.lolesport.api.player.domain.Player;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerCommonService {
    private final PlayerRepository playerRepository;

    public Player findPlayerBySummonerName(String summonerName){
        Optional<Player> optionalPlayer = this.playerRepository.findBySummonerName(summonerName);
        if(optionalPlayer.isEmpty()) throw new ResourceNotFoundException(CommonError.PLAYER_INFO_NOT_FOUND);
        return optionalPlayer.get();
    }
}
