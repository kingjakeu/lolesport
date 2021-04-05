package com.kingjakeu.promode.api.crawl.dto.game;

import com.kingjakeu.promode.api.tournament.domain.Tournament;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameTournamentDto {
    private String id;

    public Tournament toTournamentEntity(){
        return Tournament.builder().id(this.id).build();
    }
}
