package com.kingjakeu.promode.api.champion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChampionSimpleDto {
    private String id;
    private String name;
    private String imageUrl;
}
