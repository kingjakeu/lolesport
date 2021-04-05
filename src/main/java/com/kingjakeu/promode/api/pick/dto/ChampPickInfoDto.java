package com.kingjakeu.promode.api.pick.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ChampPickInfoDto {
    private String id;
    private String name;
    private Long pickCount;
}
