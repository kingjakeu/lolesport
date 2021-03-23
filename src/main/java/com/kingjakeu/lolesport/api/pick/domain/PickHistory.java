package com.kingjakeu.lolesport.api.pick.domain;

import com.kingjakeu.lolesport.api.champion.domain.Champion;
import com.kingjakeu.lolesport.api.game.domain.Game;
import com.kingjakeu.lolesport.api.player.domain.Player;
import com.kingjakeu.lolesport.api.team.domain.Team;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PICK_HISTORY")
public class PickHistory {

    @EmbeddedId
    private PickHistoryId pickHistoryId;

    @MapsId("gameId")
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "CHAMP_ID")
    private Champion champion;

    @Column(name = "PATCH_VER", length = 20)
    private String patchVersion;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;
}
