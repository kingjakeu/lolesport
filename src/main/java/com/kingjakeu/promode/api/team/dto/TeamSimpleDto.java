package com.kingjakeu.promode.api.team.dto;

import com.kingjakeu.promode.api.team.domain.Team;
import lombok.Getter;

@Getter
public class TeamSimpleDto {
    protected String id;
    protected String code;
    protected String name;
    protected String imgUrl;

    public TeamSimpleDto(Team team){
        this.id = team.getId();
        this.code = team.getCode();
        this.name = team.getName();
        this.imgUrl = team.getImageUrl();
    }
}
