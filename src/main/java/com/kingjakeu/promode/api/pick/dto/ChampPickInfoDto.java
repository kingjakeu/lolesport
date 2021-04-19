package com.kingjakeu.promode.api.pick.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ChampPickInfoDto {
    private String id;
    private String name;
//    private String imgUrl;
    private Long pickCount;
//    private Long winCount;
//    private Long lossCount;
//    private Double winRate;

    public ChampPickInfoDto(String id, String name, Long pickCount){
        this.id = id;
        this.name = name;
        this.pickCount = pickCount;
    }
}
