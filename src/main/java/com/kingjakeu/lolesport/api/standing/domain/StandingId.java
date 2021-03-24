package com.kingjakeu.lolesport.api.standing.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Embeddable
public class StandingId implements Serializable {

    @Column(name = "TOURNAMENT_ID")
    private String tournamentId;

    @Column(name = "TEAM_ID")
    private String teamId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((tournamentId == null) ? 0 : tournamentId.hashCode());
        hashCode = prime * hashCode + ((teamId == null) ? 0 : teamId.hashCode());

        return hashCode;
    }

    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Builder
    public StandingId(String tournamentId, String teamId){
        this.tournamentId = tournamentId;
        this.teamId = teamId;
    }
}
