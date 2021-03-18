package com.kingjakeu.lolesport.api.info.dto.team;

import com.kingjakeu.lolesport.api.info.domain.League;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamHomeLeagueDto {
    private String name;
    private String region;
}
