package com.kingjakeu.promode.api.match.service;

import com.kingjakeu.promode.api.match.dao.MatchRepository;
import com.kingjakeu.promode.api.match.domain.Match;
import com.kingjakeu.promode.api.match.dto.response.MatchResultResDto;
import com.kingjakeu.promode.api.tournament.domain.Tournament;
import com.kingjakeu.promode.common.constant.CommonCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchCommonService {
    private final MatchRepository matchRepository;

    public List<Match> findAllByTournament(Tournament tournament){
        return this.matchRepository.findAllByTournament(tournament);
    }

    public List<Match> findAllByMatchDate(LocalDate matchDate){
        return this.findAllByMatchDate(matchDate, Long.parseLong(CommonCode.TIMEZONE_OFFSET.getCode()));
    }

    public List<Match> findAllByMatchDate(LocalDate matchDate, long timeZoneOffset){
        LocalDateTime startTime = matchDate.atStartOfDay().minusHours(timeZoneOffset);
        LocalDateTime endTime =  matchDate.atTime(23, 59, 59).minusHours(timeZoneOffset);
        return this.findAllBetweenStartTimeAndEndTime(startTime, endTime);
    }

    public List<Match> findAllBetweenStartTimeAndEndTime(LocalDateTime startTime, LocalDateTime endTime){
        return this.matchRepository.findAllByStartTimeBetween(startTime, endTime);
    }

}
