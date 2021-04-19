package com.kingjakeu.promode.api.pick.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@ToString
public class ChampPickInfoDto {
    private String id;
    private String name;
    private String imgUrl;
    private Long pickCount;
    private Long winCount;
    private Long lossCount;
    private BigDecimal winRate;

    public ChampPickInfoDto(String id, String name, Long pickCount){
        this.id = id;
        this.name = name;
        this.pickCount = pickCount;
    }

    public void computeWinRate(Long winCount){
        this.winCount = winCount;
        this.lossCount = this.pickCount - this.winCount;
        this.winRate = new BigDecimal(this.winCount)
                .divide(new BigDecimal(this.pickCount), 3, RoundingMode.FLOOR)
                .multiply(new BigDecimal(100)).setScale(1, RoundingMode.FLOOR);
    }
}
