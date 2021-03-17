package com.kingjakeu.lolesport.api.info.dto.league;

import com.kingjakeu.lolesport.api.info.domain.League;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LeagueDto {
    private String id;
    private String slug;
    private String name;
    private String region;
    private String image;
    private Long priority;

    public League toLeagueEntity(){
        return League.builder()
                .id(this.id)
                .slug(this.slug)
                .name(this.name)
                .region(this.region)
                .imageUrl(this.image)
                .build();
    }
}
