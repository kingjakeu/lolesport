package com.kingjakeu.lolesport.api.crawl.dto.game;

import com.kingjakeu.lolesport.api.team.domain.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameTeamDto {
    private String id;
    private String side;

    public Team toTeamEntity(){
        return Team.builder().id(this.id).build();
    }
}
