package com.kingjakeu.promode.api.crawl.dto.game;

import com.kingjakeu.promode.api.league.domain.League;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameLeagueDto {
    private String id;
    private String slug;
    private String image;
    private String name;

    public League toLeagueEntity(){
        return League.builder().id(this.id).build();
    }
}
