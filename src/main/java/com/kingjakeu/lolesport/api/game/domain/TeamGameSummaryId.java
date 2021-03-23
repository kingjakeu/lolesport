package com.kingjakeu.lolesport.api.game.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Embeddable
public class TeamGameSummaryId implements Serializable {
    @Column(name = "GAME_ID")
    private String gameId;

    @Column(name = "TEAM_ID")
    private String teamId;

    @Builder
    public TeamGameSummaryId(String gameId, String teamId){
        this.gameId = gameId;
        this.teamId = teamId;
    }
}
