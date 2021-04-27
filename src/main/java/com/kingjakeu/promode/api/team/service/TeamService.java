package com.kingjakeu.promode.api.team.service;

import com.kingjakeu.promode.api.game.dao.TeamGameSummaryRepositorySupport;
import com.kingjakeu.promode.api.team.dto.TeamTournamentResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamGameSummaryRepositorySupport teamGameSummaryRepositorySupport;

    public List<TeamTournamentResultDto> getTeamInfoTournamentResult(String tournamentId, Integer pageIdx){
        return this.findTeamInfoTournamentResult(
                tournamentId,
                pageIdx == null ? 0 : pageIdx
        );
    }

    private List<TeamTournamentResultDto> findTeamInfoTournamentResult(String tournamentId, Integer pageIdx){
        Pageable pageable = PageRequest.of(pageIdx, 10);
        Page<TeamTournamentResultDto> dtoPage = this.teamGameSummaryRepositorySupport
                .findTeamTournamentResultDto(tournamentId, pageable);
        return dtoPage.getContent();
    }
}
