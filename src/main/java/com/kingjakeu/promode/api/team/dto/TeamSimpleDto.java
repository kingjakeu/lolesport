package com.kingjakeu.promode.api.team.dto;

import com.kingjakeu.promode.api.team.domain.Team;
import lombok.Getter;

@Getter
public class TeamSimpleDto {
    private String id;
    private String code;
    private String name;
    private String imgUrl;

    public TeamSimpleDto(Team team){
        this.id = team.getId();
        this.code = team.getCode();
        this.name = team.getName();
        this.imgUrl = team.getImageUrl();
    }
}
