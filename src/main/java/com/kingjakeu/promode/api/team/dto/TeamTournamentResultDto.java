package com.kingjakeu.promode.api.team.dto;

import com.kingjakeu.promode.api.standing.domain.Standing;
import com.kingjakeu.promode.api.team.domain.Team;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class TeamTournamentResultDto extends TeamSimpleDto {
    private Integer rank;
    private Integer matchWin;
    private Integer matchLoss;
    private Integer totalMatch;
    private BigDecimal matchWinRate;
    private Integer gameWin;
    private Integer gameLoss;
    private Integer totalGame;
    private Integer score;
    private BigDecimal gameWinRate;

    public TeamTournamentResultDto(Team team, Standing standing) {
        super(team);
        this.rank = standing.getRank();
        this.matchWin = standing.getMatchWin();
        this.matchLoss = standing.getMatchLoss();
        this.totalMatch = this.matchWin + this.matchLoss;
        this.gameWin = standing.getGameWin();
        this.gameLoss = standing.getGameLoss();
        this.totalGame = this.gameWin + this.gameLoss;
        this.score = standing.getScore();
        this.computeWinRate();
    }

    private void computeWinRate(){
        this.matchWinRate = BigDecimal.valueOf(this.matchWin)
                .divide(BigDecimal.valueOf(this.totalMatch), 2, RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(100));

        this.gameWinRate = BigDecimal.valueOf(this.gameWin)
                .divide(BigDecimal.valueOf(this.totalGame), 2, RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(100));
    }
}
