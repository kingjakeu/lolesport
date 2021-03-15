package com.kingjakeu.lolesport.api.info.dto.matchHistory;

import com.kingjakeu.lolesport.api.info.dto.matchHistory.BanDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
@NoArgsConstructor
public class TeamDto {
    private Long teamId;
    private String win;
    private Boolean firstBlood;
    private Boolean firstTower;
    private Boolean firstInhibitor;
    private Boolean firstBaron;
    private Boolean firstDragon;
    private Boolean firstRiftHerald;
    private Boolean towerKills;
    private Boolean inhibitorKills;
    private Integer baronKills;
    private Integer dragonKills;
    private Integer vilemawKills;
    private Integer riftHeraldKills;
    private Integer dominionVictoryScore;
    private ArrayList<BanDto> bans;
}
