package com.kingjakeu.promode.api.standing.dto.response;

import com.kingjakeu.promode.api.standing.domain.Standing;
import lombok.Getter;

@Getter
public class TeamStandingResDto {

    private String teamId;
    private String teamCode;
    private String teamName;
    private String teamSlug;
    private Integer matchWin;
    private Integer matchLoss;
    private Integer gameWin;
    private Integer gameLoss;
    private Integer rank;

    public TeamStandingResDto(Standing standing){
        this.teamId = standing.getTeam().getId();
        this.teamCode = standing.getTeam().getCode();
        this.teamName = standing.getTeam().getName();
        this.teamSlug = standing.getTeam().getSlug();
        this.matchWin = standing.getMatchWin();
        this.matchLoss = standing.getMatchLoss();
        this.gameWin = standing.getGameWin();
        this.gameLoss = standing.getGameLoss();
        this.rank =  standing.getRank();
    }
}
