package com.kingjakeu.lolesport.api.info.domain;

import com.kingjakeu.lolesport.common.constant.LolLeague;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MATCH_INFO")
public class Match {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "BLOCK_NAME")
    private String blockName;

    @Enumerated(EnumType.STRING)
    @Column(name = "LEAGUE_CODE")
    private LolLeague league;

    @Column(name = "TEAM_1")
    private String team1;

    @Column(name = "TEAM_2")
    private String team2;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "STATE")
    private String state;

}

