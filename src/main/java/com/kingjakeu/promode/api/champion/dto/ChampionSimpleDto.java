package com.kingjakeu.promode.api.champion.dto;

import com.kingjakeu.promode.api.champion.domain.Champion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChampionSimpleDto {
    private String id;
    private String name;
    private String imageUrl;

    public ChampionSimpleDto(Champion champion){
        this.id = champion.getId();
        this.name = champion.getName();
        this.imageUrl = champion.getImageUrl();
    }
}
