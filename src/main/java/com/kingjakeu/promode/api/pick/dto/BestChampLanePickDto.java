package com.kingjakeu.promode.api.pick.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BestChampLanePickDto {
    private ChampPickInfoDto top;
    private ChampPickInfoDto jug;
    private ChampPickInfoDto mid;
    private ChampPickInfoDto bot;
    private ChampPickInfoDto sup;
}
