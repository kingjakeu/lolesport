package com.kingjakeu.lolesport.api.info.dto.matchHistory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isBlueTeam(){
        return this.teamId.equals(100L);
    }

    public boolean isRedTeam(){
        return this.teamId.equals(200L);
    }

    public List<String> getBanChampionKeyList(){
        List<String> banChampionKeyList = new ArrayList<>();

        for(BanDto banDto : this.bans){
            banChampionKeyList.add(banDto.getChampionId().toString());
        }
        return banChampionKeyList;
    }
}
