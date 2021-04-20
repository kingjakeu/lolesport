package com.kingjakeu.promode.api.match.service;

import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.kingjakeu.promode.api.game.dto.GameSimpleResultDto;
import com.kingjakeu.promode.api.game.service.GameCommonService;
import com.kingjakeu.promode.api.game.service.TeamGameCommonService;
import com.kingjakeu.promode.api.match.domain.Match;
import com.kingjakeu.promode.api.match.dto.response.MatchGameResultResDto;
import com.kingjakeu.promode.api.match.dto.response.MatchResultResDto;
import com.kingjakeu.promode.api.match.dto.response.MatchTeamResDto;
import com.kingjakeu.promode.api.pick.service.PickHistoryCommonService;
import com.kingjakeu.promode.api.standing.domain.Standing;
import com.kingjakeu.promode.api.standing.service.StandingCommonService;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.api.team.dto.TeamSimpleDto;
import com.kingjakeu.promode.common.constant.CommonCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchCommonService matchCommonService;
    private final GameCommonService gameCommonService;
    private final TeamGameCommonService teamGameCommonService;
    private final StandingCommonService standingCommonService;
    private final PickHistoryCommonService pickHistoryCommonService;

    public List<MatchResultResDto> getMatch(LocalDate matchDate){
        List<Match> matchList = this.matchCommonService.findAllByMatchDate(matchDate);
        List<MatchResultResDto> matchResultResDtoList = new ArrayList<>();

        for(Match match : matchList){
            MatchResultResDto matchResultResDto = MatchResultResDto.builder()
                    .matchId(match.getId())
                    .state(match.getState())
                    .startTime(match.getStartTime().plusHours(Long.parseLong(CommonCode.TIMEZONE_OFFSET.getCode())))
                    .blueTeam(this.getMatchTeamResDto(match.getTeam1()))
                    .redTeam(this.getMatchTeamResDto(match.getTeam2()))
                    .build();

            if(match.isCompleted()){
                List<Game> gameList = this.gameCommonService.findCompletedGameByMatchId(match.getId());
                for(Game game : gameList){
                    List<TeamGameSummary> teamGameList = this.teamGameCommonService.findTeamGameSummaryByGame(game);
                    matchResultResDto.calculateGameScore(teamGameList);
                }
            }
            matchResultResDtoList.add(matchResultResDto);
        }
        return matchResultResDtoList;
    }

    public List<MatchGameResultResDto> getMatchGames(String matchId){
        List<Game> gameList = this.gameCommonService.findCompletedGameByMatchId(matchId);
        List<MatchGameResultResDto> gameResultResDto = new ArrayList<>();

        for(Game game : gameList) {
            GameSimpleResultDto gameSimpleResultDto = this.gameCommonService.findGameSimpleResultByGame(game);

            gameResultResDto.add(MatchGameResultResDto.builder()
                    .gameId(gameSimpleResultDto.getGameId())
                    .blueTeam(new TeamSimpleDto(game.getBlueTeam()))
                    .redTeam(new TeamSimpleDto(game.getRedTeam()))
                    .winTeam(gameSimpleResultDto.getWinTeam())
                    .blueKillScore(gameSimpleResultDto.getBlueKillScore())
                    .redKillScore(gameSimpleResultDto.getRedKillScore())
                    .bluePickList(this.pickHistoryCommonService
                            .findGamePickedChampionsByGameIdAndSide(game.getId(), CommonCode.BLUE_SIDE.getCode()))
                    .redPickList(this.pickHistoryCommonService
                            .findGamePickedChampionsByGameIdAndSide(game.getId(), CommonCode.RED_SIDE.getCode()))
                    .build());
        }
        return gameResultResDto;
    }

    public MatchTeamResDto getMatchTeamResDto(Team team){
        Standing standing = this.standingCommonService.findStandingByTeam(team);
        return MatchTeamResDto.builder()
                .team(team)
                .standing(standing)
                .build();
    }
}
