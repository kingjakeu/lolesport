package com.kingjakeu.lolesport.api.info.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "GAME_INFO")
public class Game {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MATCH_ID")
    private String matchId;

    @Column(name = "TOURNAMENT_ID")
    private String tournamentId;

    @Column(name = "LEAGUE_CODE")
    private String leagueCode;

    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "STATE")
    private String state;

    @Column(name = "BLUE_TEAM")
    private String blueTeam;

    @Column(name = "RED_TEAM")
    private String redTeam;

    @Column(name = "START_DATETIME")
    private LocalDateTime startTime;

    @Column(name = "START_MILLIS")
    private Long startMillis;

    @Column(name = "END_MILLIS")
    private Long endMillis;

    @Column(name = "MATCH_HISTORY_URL")
    private String matchHistoryUrl;
}
