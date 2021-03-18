package com.kingjakeu.lolesport.api.info.dto.game;

import com.kingjakeu.lolesport.api.info.domain.Tournament;
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
