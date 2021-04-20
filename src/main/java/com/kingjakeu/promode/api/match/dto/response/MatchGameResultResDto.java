package com.kingjakeu.promode.api.match.dto.response;

import com.kingjakeu.promode.api.champion.dto.ChampionSimpleDto;
import com.kingjakeu.promode.api.pick.dto.GamePickedChampionsDto;
import com.kingjakeu.promode.api.team.dto.TeamSimpleDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
public class MatchGameResultResDto {
    private String gameId;
    private TeamSimpleDto blueTeam;
    private TeamSimpleDto redTeam;
    private String winTeam;
    private Long blueKillScore;
    private Long redKillScore;
    private GamePickedChampionsDto bluePickList;
    private GamePickedChampionsDto redPickList;
}
