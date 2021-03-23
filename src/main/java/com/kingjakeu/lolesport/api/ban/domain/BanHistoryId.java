package com.kingjakeu.lolesport.api.ban.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class BanHistoryId implements Serializable {
    @Column(name = "GAME_ID")
    private String gameId;

    @Column(name = "BAN_TURN", length = 2)
    private Integer banTurn;

    @Column(name = "SIDE", length = 5)
    private String side;

    @Builder
    public BanHistoryId(String gameId, Integer banTurn, String side){
        this.gameId = gameId;
        this.banTurn = banTurn;
        this.side = side;
    }
}
