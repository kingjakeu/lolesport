package com.kingjakeu.lolesport.api.tournament.service;

import com.kingjakeu.lolesport.api.tournament.dao.TournamentRepository;
import com.kingjakeu.lolesport.api.tournament.domain.Tournament;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    public Tournament findTournamentById(String id){
        Optional<Tournament> tournamentOptional = this.tournamentRepository.findById(id);
        if(tournamentOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.TOURNAMENT_INFO_NOT_FOUND);
        return tournamentOptional.get();
    }
}
