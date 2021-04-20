package com.kingjakeu.promode.api.pick.dto;

import com.kingjakeu.promode.api.champion.dto.ChampionSimpleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamePickedChampionsDto {
    private String gameId;
    private ChampionSimpleDto top;
    private ChampionSimpleDto jug;
    private ChampionSimpleDto mid;
    private ChampionSimpleDto bot;
    private ChampionSimpleDto sup;

    public GamePickedChampionsDto(String gameId){
        this.gameId = gameId;
    }
}
