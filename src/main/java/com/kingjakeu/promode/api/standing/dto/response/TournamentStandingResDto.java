package com.kingjakeu.promode.api.standing.dto.response;

import com.kingjakeu.promode.api.standing.domain.Standing;
import com.kingjakeu.promode.api.tournament.domain.Tournament;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TournamentStandingResDto {

    private String tournamentId;
    private List<TeamStandingResDto> teamStandings;

    public TournamentStandingResDto(Tournament tournament, List<Standing> standingList){
        this.tournamentId = tournament.getId();
        this.teamStandings = new ArrayList<>();
        for(Standing standing : standingList) {
            this.teamStandings.add(new TeamStandingResDto(standing));
        }
    }
}
