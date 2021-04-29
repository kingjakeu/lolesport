package com.kingjakeu.promode.api.game.dto;

import com.kingjakeu.promode.common.constant.LolRole;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@ToString
public class PlayerStatDto {

    private String id;
    private String summonerName;
    private LolRole role;
    private BigDecimal kill;
    private BigDecimal death;
    private BigDecimal assist;
    private BigDecimal kda;
    private Long win;
    private Long loss;
    private Long totalGame;
    private BigDecimal winRate;

    public PlayerStatDto(String id,
                         String summonerName,
                         LolRole role,
                         Long totalKill,
                         Long totalDeath,
                         Long totalAssist,
                         Long win,
                         Long totalGame){
        this.id = id;
        this.summonerName = summonerName;
        this.role = role;
        this.win = win;
        this.totalGame = totalGame;
        this.loss = this.totalGame - this.win;
        this.computeWinRate();;

        BigDecimal totalGameCount = new BigDecimal(this.totalGame);
        this.kill = BigDecimal.valueOf(totalKill).divide(totalGameCount, 1, RoundingMode.FLOOR);
        this.death = BigDecimal.valueOf(totalDeath).divide(totalGameCount, 1, RoundingMode.FLOOR);
        this.assist = BigDecimal.valueOf(totalAssist).divide(totalGameCount, 1, RoundingMode.FLOOR);
        this.computeKda();
    }

    public PlayerStatDto(String id,
                         String summonerName,
                         LolRole role,
                         Double kill,
                         Double death,
                         Double assist,
                         Long win,
                         Long totalGame){
        this.id = id;
        this.summonerName = summonerName;
        this.role = role;
        this.kill = BigDecimal.valueOf(kill).setScale(1, RoundingMode.FLOOR);
        this.death = BigDecimal.valueOf(death).setScale(1, RoundingMode.FLOOR);
        this.assist = BigDecimal.valueOf(assist).setScale(1, RoundingMode.FLOOR);
        this.computeKda();
        this.win = win;
        this.totalGame = totalGame;
        this.loss = this.totalGame - this.win;
        this.computeWinRate();

    }

    private void computeKda(){
        this.kda = this.kill.add(this.assist).divide(this.death, 2, RoundingMode.FLOOR);
    }

    private void computeWinRate(){
        this.winRate = BigDecimal.valueOf(this.win).divide(BigDecimal.valueOf(this.totalGame), 1 ,RoundingMode.FLOOR).multiply(BigDecimal.valueOf(100));
    }
}
