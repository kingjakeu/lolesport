package com.kingjakeu.lolesport.api.info.dto.team;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class TeamDto {
    private String id;
    private String slug;
    private String name;
    private String code;
    private String image;
    private String alternativeImage;
    private String backgroundImage;
    private String status;
    private TeamHomeLeagueDto homeLeague;
    private ArrayList<TeamPlayerDto> players;

    public boolean isLckTeam(){
        return this.homeLeague != null && this.homeLeague.getName().equals("LCK");
    }
}
