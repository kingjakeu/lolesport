package com.kingjakeu.promode.api.match.dto.response;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@ToString
public class MatchResultResDto {
    private String matchId;
    private String state;
    private String startTime;
    private MatchTeamResDto blueTeam;
    private MatchTeamResDto redTeam;

    private Integer blueScore;
    private Integer redScore;

    @Builder
    public MatchResultResDto(String matchId,
                             String state,
                             String startTime,
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

    public void addBlueScore(){
        this.blueScore += 1;
    }

    public void addRedScore(){
        this.redScore += 1;
    }

}
