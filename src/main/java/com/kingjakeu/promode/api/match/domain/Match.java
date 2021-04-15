package com.kingjakeu.promode.api.match.domain;

import com.kingjakeu.promode.api.tournament.domain.Tournament;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.common.constant.CommonCode;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "ID", length = 20)
    private String id;

    @Column(name = "BLOCK_NAME", length = 100)
    private String blockName;

    @Setter
    @ManyToOne
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;

    @Setter
    @ManyToOne
    @JoinColumn(name = "TEAM_1_ID")
    private Team team1;

    @Setter
    @ManyToOne
    @JoinColumn(name = "TEAM_2_ID")
    private Team team2;

    @Column(name = "START_TIME", columnDefinition = "datetime")
    private LocalDateTime startTime;

    @Column(name = "STATE", length = 20)
    private String state;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;

    public boolean startDateEqualsTo(LocalDate localDate){
        return this.startTime.toLocalDate().equals(localDate);
    }

    public boolean isStartDateBetween(LocalDate startDate, LocalDate endDate){
        return (this.startTime.toLocalDate().isEqual(startDate) || this.startTime.toLocalDate().isAfter(startDate))
                && (this.startTime.toLocalDate().isEqual(endDate) || this.startTime.toLocalDate().isBefore(endDate));
    }

    public boolean isCompleted(){
        return CommonCode.STATE_COMPLETED.codeEqualsTo(this.state);
    }
}

