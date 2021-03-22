package com.kingjakeu.lolesport.api.league.service;

import com.kingjakeu.lolesport.api.league.dao.LeagueRepository;
import com.kingjakeu.lolesport.api.league.domain.League;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeagueCommonService {

    private final LeagueRepository leagueRepository;

    public League findLeagueById(String leagueId){
        Optional<League> leagueOptional = this.leagueRepository.findById(leagueId);
        if (leagueOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.LEAGUE_INFO_NOT_FOUND);
        return leagueOptional.get();
    }

    public League findLeagueByName(String name){
        Optional<League> leagueOptional = this.leagueRepository.findByName(name);
        if (leagueOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.LEAGUE_INFO_NOT_FOUND);
        return leagueOptional.get();
    }
}
