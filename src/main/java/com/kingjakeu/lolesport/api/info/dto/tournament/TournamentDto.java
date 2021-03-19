package com.kingjakeu.lolesport.api.info.dto.tournament;

import com.kingjakeu.lolesport.api.info.domain.League;
import com.kingjakeu.lolesport.api.info.domain.Tournament;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TournamentDto {
    private String id;
    private String slug;
    private String startDate;
    private String endDate;

    public Tournament toTournamentEntity(League league){
        return Tournament.builder()
                .id(this.id)
                .slug(this.slug)
                .league(league)
                .startDate(LocalDate.parse(this.startDate))
                .endDate(LocalDate.parse(this.endDate))
                .build();
    }
}