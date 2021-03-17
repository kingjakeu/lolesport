package com.kingjakeu.lolesport.api.info.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TOURNAMENT_INFO")
public class Tournament {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;
}
