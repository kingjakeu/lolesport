package com.kingjakeu.promode.api.team.dto;

import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.api.team.dto.TeamSimpleDto;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class TeamTournamentResultDto extends TeamSimpleDto {
    private Long win;
    private Long loss;
    private Long totalGame;
    private BigDecimal winRate;

    public TeamTournamentResultDto(Team team, Long win, Long totalGame) {
        super(team);
        this.win = win;
        this.totalGame = totalGame;
        this.loss = this.totalGame - this.win;
        this.computeWinRate();
    }

    private void computeWinRate(){
        this.winRate = BigDecimal.valueOf(this.win)
                .divide(BigDecimal.valueOf(this.totalGame), 2, RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(100));
    }
}
