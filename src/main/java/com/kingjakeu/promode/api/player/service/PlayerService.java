package com.kingjakeu.promode.api.player.service;

import com.kingjakeu.promode.api.game.dao.PlayerGameSummaryRepositorySupport;
import com.kingjakeu.promode.api.game.dto.PlayerAverageSummaryDto;
import com.kingjakeu.promode.common.constant.LolRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerGameSummaryRepositorySupport playerGameSummaryRepositorySupport;

    public List<PlayerAverageSummaryDto> getPlayerSummaryList(String role, Integer pageIdx){
        return this.findPlayerAverageSummaryListByRole(
                role == null ? null : LolRole.findBySlugName(role),
                pageIdx == null ? 0 : pageIdx
        );
    }

    private List<PlayerAverageSummaryDto> findPlayerAverageSummaryListByRole(LolRole role, int pageIdx){
        Pageable pageable = PageRequest.of(pageIdx, 10);
        Page<PlayerAverageSummaryDto> dtoPage = this.playerGameSummaryRepositorySupport.findPlayerAverageSummary(role, pageable);
        return dtoPage.getContent();
    }
}
