package com.kingjakeu.promode.api.tournament.service;

import com.kingjakeu.promode.api.tournament.dao.TournamentRepository;
import com.kingjakeu.promode.api.tournament.domain.Tournament;
import com.kingjakeu.promode.common.constant.CommonError;
import com.kingjakeu.promode.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentCommonService {
    private final TournamentRepository tournamentRepository;

    public Tournament findTournamentById(String id){
        Optional<Tournament> tournamentOptional = this.tournamentRepository.findById(id);
        if(tournamentOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.TOURNAMENT_INFO_NOT_FOUND);
        return tournamentOptional.get();
    }
}
