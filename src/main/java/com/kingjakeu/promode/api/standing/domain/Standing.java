package com.kingjakeu.promode.api.standing.domain;

import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.api.tournament.domain.Tournament;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STANDING")
public class Standing {
    @EmbeddedId
    private StandingId standingId;

    @Setter
    @MapsId("tournamentId")
    @ManyToOne
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;

    @Setter
    @MapsId("teamId")
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "MATCH_WIN")
    private Integer matchWin;

    @Column(name = "MATCH_LOSS")
    private Integer matchLoss;

    @Column(name = "GAME_WIN")
    private Integer gameWin;

    @Column(name = "GAME_LOSS")
    private Integer gameLoss;

    @Column(name = "SCORE")
    private Integer score;

    @Setter
    @Column(name = "RANK")
    private Integer rank;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;

    public Standing(StandingId standingId, Tournament tournament, Team team){
        this.standingId = standingId;
        this.tournament = tournament;
        this.team = team;
        this.matchWin = 0;
        this.matchLoss = 0;
        this.gameWin = 0;
        this.gameLoss = 0;
    }

    public void accumulate(int matchGameCount, int gameWinCount){
        int gameLossCount = matchGameCount - gameWinCount;
        if (gameWinCount > gameLossCount){
            this.matchWin += 1;
        }else{
            this.matchLoss += 1;
        }
        this.gameWin += gameWinCount;
        this.gameLoss += gameLossCount;
        this.score = this.gameWin - this.gameLoss;
    }
}
