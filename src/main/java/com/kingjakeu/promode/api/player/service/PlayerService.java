package com.kingjakeu.promode.api.player.service;

import com.kingjakeu.promode.api.game.dao.PlayerGameSummaryRepositorySupport;
import com.kingjakeu.promode.api.game.dto.PlayerStatDto;
import com.kingjakeu.promode.common.constant.LolRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerGameSummaryRepositorySupport playerGameSummaryRepositorySupport;

    public List<PlayerStatDto> getPlayerSummaryList(String role, Integer pageIdx){
        return this.findPlayerAverageSummaryListByRole(
                role == null ? null : LolRole.findBySlugName(role),
                pageIdx == null ? 0 : pageIdx
        );
    }

    private List<PlayerStatDto> findPlayerAverageSummaryListByRole(LolRole role, int pageIdx){
        Pageable pageable = PageRequest.of(pageIdx, 10);
        Page<PlayerStatDto> dtoPage = this.playerGameSummaryRepositorySupport.findPlayerAverageSummary(role, pageable);
        return dtoPage.getContent();
    }

    /**
     * best 플레이어 리스트 조회
     * @param tournamentId 토너먼트 아이디
     * @param weekBlock week
     * @return 플레이어 리스트
     */
    public List<PlayerStatDto> getBestPlayerStatList(String tournamentId, String weekBlock){
        List<PlayerStatDto> bestPlayerList = new ArrayList<>();
        for(LolRole lolRole : LolRole.playerValues()){
            List<PlayerStatDto> playerStatDtoList = this.playerGameSummaryRepositorySupport
                    .findPlayerStatDtoList(tournamentId, weekBlock, lolRole);

            // best player sort
            playerStatDtoList.sort(new Comparator<PlayerStatDto>() {
                @Override
                public int compare(PlayerStatDto p1, PlayerStatDto p2) {
                    return p2.getKda().compareTo(p1.getKda());
                }
            });
            bestPlayerList.add(playerStatDtoList.get(0));
        }
        return bestPlayerList;
    }
}
