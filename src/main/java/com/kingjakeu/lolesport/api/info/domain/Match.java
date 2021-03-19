package com.kingjakeu.lolesport.api.info.domain;

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

    @ManyToOne
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

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
}

