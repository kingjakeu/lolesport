package com.kingjakeu.promode.api.match.service;

import com.kingjakeu.promode.api.match.domain.Match;
import com.kingjakeu.promode.api.match.dto.response.MatchResultResDto;
import com.kingjakeu.promode.api.match.dto.response.MatchTeamResDto;
import com.kingjakeu.promode.common.constant.DateTimeFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchCommonService matchCommonService;

    public void getMatch(String matchDate){
        LocalDateTime startTime = LocalDateTime.parse(matchDate+"000000", DateTimeFormat.REQUEST_DATETIME);
        LocalDateTime endTime = LocalDateTime.parse(matchDate+"235959", DateTimeFormat.REQUEST_DATETIME);

        List<Match> matchList = this.matchCommonService.findAllBetweenStartTimeAndEndTime(startTime, endTime);
        for(Match match : matchList){
            MatchResultResDto matchResultResDto = MatchResultResDto.builder()
                    .matchId(match.getId())
                    .state(match.getState())
                    .startTime(match.getStartTime().toString())
                    .blueTeam(new MatchTeamResDto(match.getTeam1()))
                    .redTeam(new MatchTeamResDto(match.getTeam2()))
                    .build();

            if(match.isCompleted()){

            }
        }
    }
}
