package com.kingjakeu.lolesport.api.game.domain;

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
@Table(name = "TEAM_GAME_SMRY")
public class TeamGameSummary {
    @Setter
    @EmbeddedId
    private TeamGameSummaryId teamGameSummaryId;

    @Setter
    @MapsId("gameId")
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @Setter
    @MapsId("teamId")
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Setter
    @Column(name = "SIDE", length = 5)
    private String side;

    @Column(name = "WIN")
    private Boolean win;

    @Column(name = "FIST_BLOOD")
    private Boolean firstBlood;

    @Column(name = "FIST_TOWER")
    private Boolean firstTower;

    @Column(name = "FIST_INHIBIT")
    private Boolean firstInhibitor;

    @Column(name = "FIST_BARON")
    private Boolean firstBaron;

    @Column(name = "FIST_DRAGON")
    private Boolean firstDragon;

    @Column(name = "FIST_RIFTH")
    private Boolean firstRiftHerald;

    @Column(name = "TOWER_KILL")
    private Integer towerKill;

    @Column(name = "INHIBIT_KILL")
    private Integer inhibitorKill;

    @Column(name = "BARON_KILL")
    private Integer baronKill;

    @Column(name = "DRAGON_KILL")
    private Integer dragonKill;

    @Column(name = "RIFTH_KILL")
    private Integer riftHeraldKill;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;
}
