package com.kingjakeu.promode.api.pick.service;

import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.game.service.GameCommonService;
import com.kingjakeu.promode.api.game.service.TeamGameCommonService;
import com.kingjakeu.promode.api.match.domain.Match;
import com.kingjakeu.promode.api.match.service.MatchCommonService;
import com.kingjakeu.promode.api.pick.dao.PickHistoryRepository;
import com.kingjakeu.promode.api.pick.dao.PickHistoryRepositorySupport;
import com.kingjakeu.promode.api.pick.domain.PickHistory;
import com.kingjakeu.promode.api.pick.dto.BestChampLanePickDto;
import com.kingjakeu.promode.api.pick.dto.ChampPickInfoDto;
import com.kingjakeu.promode.common.constant.CommonCode;
import com.kingjakeu.promode.common.constant.LolRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PickHistoryInfoService {
    private final MatchCommonService matchCommonService;
    private final GameCommonService gameCommonService;
    private final PickHistoryRepository pickHistoryRepository;
    private final PickHistoryRepositorySupport pickHistoryRepositorySupport;


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

    /**
     * 금주 베스트 챔피언 픽 조회
     * @param matchDate 경기 날짜
     * @return 베스트 픽
     */
    public Map<String, ChampPickInfoDto> getBestPickOfTheWeek(LocalDate matchDate){
        List<Match> matchList = this.matchCommonService.findAllByMatchDate(matchDate);
        List<Game> gameList = new LinkedList<>();
        for(Match match : matchList){
            gameList.addAll(this.gameCommonService.findCompletedGameByMatch(match));
        }

        Map<String, ChampPickInfoDto> bestChampPick = new LinkedHashMap<>();
        for(LolRole lolRole : LolRole.playerValues()){
            List<ChampPickInfoDto> result = this.pickHistoryRepositorySupport
                    .findChampPickByLaneAndGameList(lolRole, gameList);
            //TODO
            ChampPickInfoDto champPickInfoDto = result.get(0);
            Long winCount = this.pickHistoryRepositorySupport
                    .countGameResultByChampIdAndGameList(champPickInfoDto.getId(), gameList, true);
            champPickInfoDto.computeWinRate(winCount);

            bestChampPick.put(lolRole.getSlugName(), champPickInfoDto);
        }
        return bestChampPick;
    }
}
