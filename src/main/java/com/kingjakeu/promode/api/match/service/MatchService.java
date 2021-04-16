package com.kingjakeu.promode.api.match.service;

import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.kingjakeu.promode.api.game.service.GameCommonService;
import com.kingjakeu.promode.api.game.service.TeamGameCommonService;
import com.kingjakeu.promode.api.match.domain.Match;
import com.kingjakeu.promode.api.match.dto.response.MatchResultResDto;
import com.kingjakeu.promode.api.match.dto.response.MatchTeamResDto;
import com.kingjakeu.promode.common.constant.DateTimeFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchCommonService matchCommonService;
    private final GameCommonService gameCommonService;
    private final TeamGameCommonService teamGameCommonService;

    public List<MatchResultResDto>  getMatch(String matchDate){
        LocalDateTime startTime = LocalDateTime.parse(matchDate+"000000", DateTimeFormat.REQUEST_DATETIME);
        LocalDateTime endTime = LocalDateTime.parse(matchDate+"235959", DateTimeFormat.REQUEST_DATETIME);

        List<Match> matchList = this.matchCommonService.findAllBetweenStartTimeAndEndTime(startTime, endTime);
        List<MatchResultResDto> matchResultResDtoList = new ArrayList<>();
        for(Match match : matchList){
            MatchResultResDto matchResultResDto = MatchResultResDto.builder()
                    .matchId(match.getId())
                    .state(match.getState())
                    .startTime(match.getStartTime().toString())
                    .blueTeam(new MatchTeamResDto(match.getTeam1()))
                    .redTeam(new MatchTeamResDto(match.getTeam2()))
                    .build();

            if(match.isCompleted()){
                List<Game> gameList = this.gameCommonService.findCompletedGameByMatch(match);
                for(Game game : gameList){
                    List<TeamGameSummary> teamGameList = this.teamGameCommonService.findTeamGameSummaryByGame(game);

                    for(TeamGameSummary teamGameSummary : teamGameList){
                        if(match.getTeam1().idEqualsTo(teamGameSummary.getTeamGameSummaryId().getTeamId())
                        && teamGameSummary.isWin()) {
                            matchResultResDto.addBlueScore();
                        }
                        if(match.getTeam2().idEqualsTo(teamGameSummary.getTeamGameSummaryId().getTeamId())
                                && teamGameSummary.isWin()) {
                            matchResultResDto.addRedScore();
                        }
                    }
                }
            }
            matchResultResDtoList.add(matchResultResDto);
        }
        return matchResultResDtoList;
    }
}
