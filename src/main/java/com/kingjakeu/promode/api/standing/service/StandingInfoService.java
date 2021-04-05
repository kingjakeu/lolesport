package com.kingjakeu.promode.api.standing.service;

import com.kingjakeu.promode.api.standing.dao.StandingRepository;
import com.kingjakeu.promode.api.standing.domain.Standing;
import com.kingjakeu.promode.api.standing.dto.response.TournamentStandingResDto;
import com.kingjakeu.promode.api.tournament.domain.Tournament;
import com.kingjakeu.promode.api.tournament.service.TournamentCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StandingInfoService {
    private final StandingRepository standingRepository;
    private final TournamentCommonService tournamentCommonService;

    public TournamentStandingResDto getStandingByTournament(String tournamentId){
        Tournament tournament = this.tournamentCommonService.findTournamentById(tournamentId);
        List<Standing> standingList = this.standingRepository.findAllByTournamentOrderByRankAsc(tournament);
        return new TournamentStandingResDto(tournament, standingList);
    }
}
