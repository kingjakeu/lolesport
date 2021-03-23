package com.kingjakeu.lolesport.api.pick.domain;

import com.kingjakeu.lolesport.common.constant.LolRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PickHistoryId implements Serializable {
    @Column(name = "GAME_ID")
    private String gameId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 5)
    private LolRole role;

    @Column(name = "SIDE", length = 5)
    private String side;

    @Builder
    private PickHistoryId(LolRole role, String side){
        this.role = role;
        this.side = side;
    }
}
