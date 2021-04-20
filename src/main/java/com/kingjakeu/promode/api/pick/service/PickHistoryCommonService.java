package com.kingjakeu.promode.api.pick.service;

import com.kingjakeu.promode.api.champion.domain.Champion;
import com.kingjakeu.promode.api.champion.dto.ChampionSimpleDto;
import com.kingjakeu.promode.api.pick.dao.PickHistoryRepository;
import com.kingjakeu.promode.api.pick.dao.PickHistoryRepositorySupport;
import com.kingjakeu.promode.api.pick.domain.PickHistory;
import com.kingjakeu.promode.api.pick.dto.GamePickedChampionsDto;
import com.kingjakeu.promode.common.constant.CommonCode;
import com.kingjakeu.promode.common.constant.LolRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PickHistoryCommonService {
    private final PickHistoryRepository pickHistoryRepository;
    private final PickHistoryRepositorySupport pickHistoryRepositorySupport;

    /**
     * 게임에서 픽된 챔피언 정보 리턴
     * @param gameId 게임 아이디
     * @param side 진영
     * @return 챔피언 정보
     */
    public GamePickedChampionsDto findGamePickedChampionsByGameIdAndSide(String gameId, String side){
        List<PickHistory> pickHistoryList = this.pickHistoryRepositorySupport.findPickHistoriesByGameIdAndSide(gameId, side);

        GamePickedChampionsDto gamePickedChampionsDto = new GamePickedChampionsDto(gameId);
        for(PickHistory pickHistory : pickHistoryList){
            LolRole role = pickHistory.getPickHistoryId().getRole();
            ChampionSimpleDto championSimpleDto = new ChampionSimpleDto(pickHistory.getChampion());
            if(LolRole.TOP.equals(role)){
                gamePickedChampionsDto.setTop(championSimpleDto);
            }else if(LolRole.JUG.equals(role)){
                gamePickedChampionsDto.setJug(championSimpleDto);
            }else if(LolRole.MID.equals(role)){
                gamePickedChampionsDto.setMid(championSimpleDto);
            }else if(LolRole.BOT.equals(role)){
                gamePickedChampionsDto.setBot(championSimpleDto);
            }else if(LolRole.SUP.equals(role)){
                gamePickedChampionsDto.setSup(championSimpleDto);
            }
        }
        return gamePickedChampionsDto;
    }
}
