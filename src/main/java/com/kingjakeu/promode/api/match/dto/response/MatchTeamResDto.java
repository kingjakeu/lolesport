package com.kingjakeu.promode.api.match.dto.response;

import com.kingjakeu.promode.api.team.domain.Team;

public class MatchTeamResDto {
    private String id;
    private String code;
    private String name;
    private String slug;

    public MatchTeamResDto(Team team){
        this.id = team.getId();
        this.code = team.getCode();
        this.name = team.getName();
        this.slug = team.getSlug();
    }
}
