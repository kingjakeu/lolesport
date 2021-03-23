package com.kingjakeu.lolesport.api.game.domain;

import com.kingjakeu.lolesport.api.match.domain.Match;
import com.kingjakeu.lolesport.api.tournament.domain.Tournament;
import com.kingjakeu.lolesport.api.league.domain.League;
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
@Table(name = "GAME_INFO")
public class Game {
    @Id
    @Column(name = "ID", length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "MATCH_ID")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @Column(name = "GAME_SEQ", length = 2)
    private Integer sequence;

    @Column(name = "STATE", length = 20)
    private String state;

    @ManyToOne
    @JoinColumn(name = "BLUE_TEAM_ID")
    private Team blueTeam;

    @ManyToOne
    @JoinColumn(name = "RED_TEAM_ID")
    private Team redTeam;

    @Setter
    @Column(name = "PATCH_VER", length = 20)
    private String patchVersion;

    @Column(name = "START_DATETIME", columnDefinition = "datetime")
    private LocalDateTime startTime;

    @Column(name = "START_MILLIS", length = 10)
    private Long startMillis;

    @Column(name = "END_MILLIS", length = 10)
    private Long endMillis;

    @Setter
    @Lob
    @Column(name = "MATCH_HISTORY_URL")
    private String matchHistoryUrl;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;

    public boolean isMatchHistoryLinkEmpty(){
        return this.matchHistoryUrl == null;
    }
}
