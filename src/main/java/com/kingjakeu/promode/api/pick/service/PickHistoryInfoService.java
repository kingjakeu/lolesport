package com.kingjakeu.promode.api.pick.service;

import com.kingjakeu.promode.api.pick.dao.PickHistoryRepository;
import com.kingjakeu.promode.api.pick.dto.ChampPickInfoDto;
import com.kingjakeu.promode.common.constant.CommonCode;
import com.kingjakeu.promode.common.constant.LolRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PickHistoryInfoService {
    private final PickHistoryRepository pickHistoryRepository;

    public Map<String, ChampPickInfoDto> findMostPickByTeamInTournament(String tournament, String teamId){
        Map<String, ChampPickInfoDto> result = new LinkedHashMap<>();

        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "pickCount"));
        for(LolRole lolRole : LolRole.playerValues()){
            Page<ChampPickInfoDto> champPickInfoDtoPage = this.pickHistoryRepository.findChampPickInfoInTournamentByTeamIdAndLaneRole(
                    tournament, teamId, CommonCode.BLUE_SIDE.getCode(), CommonCode.RED_SIDE.getCode(), lolRole, pageable
            );
            result.put(lolRole.getSlugName(), champPickInfoDtoPage.getContent().get(0));
        }
        return result;
    }
}
