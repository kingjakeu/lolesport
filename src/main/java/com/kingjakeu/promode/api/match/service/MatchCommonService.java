package com.kingjakeu.promode.api.match.service;

import com.kingjakeu.promode.api.match.dao.MatchRepository;
import com.kingjakeu.promode.api.match.domain.Match;
import com.kingjakeu.promode.api.tournament.domain.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchCommonService {
    private final MatchRepository matchRepository;

    public List<Match> findAllByTournament(Tournament tournament){
        return this.matchRepository.findAllByTournament(tournament);
    }

    public List<Match> findAllBetweenStartTimeAndEndTime(LocalDateTime startTime, LocalDateTime endTime){
        return this.matchRepository.findAllByStartTimeBetween(startTime, endTime);
    }
}
