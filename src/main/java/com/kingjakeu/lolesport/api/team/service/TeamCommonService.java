package com.kingjakeu.lolesport.api.team.service;

import com.kingjakeu.lolesport.api.team.dao.TeamRepository;
import com.kingjakeu.lolesport.api.team.domain.Team;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
