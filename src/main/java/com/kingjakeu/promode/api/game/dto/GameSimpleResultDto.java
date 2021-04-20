package com.kingjakeu.promode.api.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GameSimpleResultDto {
    private String gameId;
    private String winTeam;
    private Long blueKillScore;
    private Long redKillScore;
}
