package com.kingjakeu.promode.api.crawl.dto.team;

import com.kingjakeu.promode.api.league.domain.League;
import com.kingjakeu.promode.api.player.domain.Player;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.common.constant.CommonCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public boolean isActiveTeam(){
        return CommonCode.TEAM_STATUS_ACTIVE.codeEqualsTo(this.status);
    }

    public boolean leagueNameEqualsTo(String leagueName){
        if(this.homeLeague == null) return false;
        return leagueName.equals(this.homeLeague.getName());
    }

    public Team toTeamEntity(League league){
         return Team.builder()
                 .id(this.id)
                 .code(this.code)
                 .name(this.name)
                 .slug(this.slug)
                 .league(league)
                 .imageUrl(this.alternativeImage)
                 .build();
    }

    public List<Player> toPlayerEntities(Team team){
        if(this.players == null) return Collections.emptyList();

        List<Player> playerList = new ArrayList<>();
        for(TeamPlayerDto teamPlayerDto : this.players){
            playerList.add(teamPlayerDto.toPlayerEntity(team));
        }
        return playerList;
    }
}
