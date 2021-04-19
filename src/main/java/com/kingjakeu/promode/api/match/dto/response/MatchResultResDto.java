package com.kingjakeu.promode.api.match.dto.response;

import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString
public class MatchResultResDto {
    private String matchId;
    private String state;
    private LocalDateTime startTime;
    private MatchTeamResDto blueTeam;
    private MatchTeamResDto redTeam;

    private Integer blueScore;
    private Integer redScore;

    @Builder
    public MatchResultResDto(String matchId,
                             String state,
                             LocalDateTime startTime,
                             MatchTeamResDto blueTeam,
                             MatchTeamResDto redTeam){
        this.matchId = matchId;
        this.state = state;
        this.startTime = startTime;
        this.blueTeam = blueTeam;
        this.redTeam = redTeam;
        this.blueScore = 0;
        this.redScore = 0;
    }

    public void calculateGameScore(List<TeamGameSummary> teamGameList){
        for(TeamGameSummary teamGameSummary : teamGameList){
            if(this.blueTeam.getId().equals(teamGameSummary.getTeamGameSummaryId().getTeamId())
                    && teamGameSummary.isWin()) {
                this.addBlueScore();
            }
            if(this.redTeam.getId().equals(teamGameSummary.getTeamGameSummaryId().getTeamId())
                    && teamGameSummary.isWin()) {
                this.addRedScore();
            }
        }
    }

    private void addBlueScore(){
        this.blueScore += 1;
    }

    private void addRedScore(){
        this.redScore += 1;
    }

}
