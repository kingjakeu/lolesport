package com.kingjakeu.promode.api.match.dto.response;

import com.kingjakeu.promode.api.standing.domain.Standing;
import com.kingjakeu.promode.api.team.domain.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MatchTeamResDto {
    private String id;
    private String code;
    private String name;
    private String slug;
    private String imgUrl;
    private Integer matchWin;
    private Integer matchLoss;

    @Builder
    public MatchTeamResDto(Team team, Standing standing){
        this.id = team.getId();
        this.code = team.getCode();
        this.name = team.getName();
        this.slug = team.getSlug();
        this.imgUrl = team.getImageUrl();
        this.matchWin = standing.getMatchWin();
        this.matchLoss = standing.getMatchLoss();
    }
}
