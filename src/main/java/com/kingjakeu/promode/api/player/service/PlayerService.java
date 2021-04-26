package com.kingjakeu.promode.api.player.service;

import com.kingjakeu.promode.api.game.dao.PlayerGameSummaryRepositorySupport;
import com.kingjakeu.promode.api.game.dto.PlayerAverageSummaryDto;
import com.kingjakeu.promode.common.constant.LolRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerGameSummaryRepositorySupport playerGameSummaryRepositorySupport;

    public List<PlayerAverageSummaryDto> getPlayerSummaryList(String role){
        LolRole lolRole = role == null ? null : LolRole.findBySlugName(role);
        return this.findPlayerAverageSummaryListByRole(lolRole);
    }

    private List<PlayerAverageSummaryDto> findPlayerAverageSummaryListByRole(LolRole role){
        return this.playerGameSummaryRepositorySupport.findPlayerAverageSummary(role);
    }
}
