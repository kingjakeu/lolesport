package com.kingjakeu.lolesport.api.info.dto.tournament;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class TournamentDataDto {
    private ArrayList<TournamentLeagueDto> leagues;
}
