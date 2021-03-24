package com.kingjakeu.lolesport.api.match.service;

import com.kingjakeu.lolesport.api.match.dao.MatchRepository;
import com.kingjakeu.lolesport.api.match.domain.Match;
import com.kingjakeu.lolesport.api.tournament.domain.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchCommonService {
    private final MatchRepository matchRepository;

    public List<Match> findAllByTournament(Tournament tournament){
        return this.matchRepository.findAllByTournament(tournament);
    }
}
