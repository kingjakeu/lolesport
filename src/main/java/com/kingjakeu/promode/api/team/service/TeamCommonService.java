package com.kingjakeu.promode.api.team.service;

import com.kingjakeu.promode.api.league.domain.League;
import com.kingjakeu.promode.api.team.dao.TeamRepository;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.common.constant.CommonError;
import com.kingjakeu.promode.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamCommonService {
    private final TeamRepository teamRepository;

    public Team findTeamByName(String teamName) {
        Optional<Team> teamOptional = this.teamRepository.findByName(teamName);
        if(teamOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.TEAM_INFO_NOT_FOUND);
        return teamOptional.get();
    }

    public List<Team> findAllTeamByLeague(League league){
        return this.teamRepository.findAllByLeagueId(league.getId());
    }
}
