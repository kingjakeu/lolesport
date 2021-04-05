package com.kingjakeu.promode.api.player.service;

import com.kingjakeu.promode.api.player.dao.PlayerRepository;
import com.kingjakeu.promode.api.player.domain.Player;
import com.kingjakeu.promode.common.constant.CommonError;
import com.kingjakeu.promode.common.exception.ResourceNotFoundException;
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
