package com.kingjakeu.promode.api.match.dto.response;

import lombok.Builder;
import lombok.Setter;

public class MatchResultResDto {
    private String matchId;
    private String state;
    private String startTime;
    private MatchTeamResDto blueTeam;
    private MatchTeamResDto redTeam;

    @Setter
    private Integer blueScore;
    @Setter
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
    }
}
