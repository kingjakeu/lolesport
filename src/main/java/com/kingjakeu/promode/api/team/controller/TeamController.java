package com.kingjakeu.promode.api.team.controller;

import com.kingjakeu.promode.api.team.dto.TeamTournamentResultDto;
import com.kingjakeu.promode.api.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/")
    public List<TeamTournamentResultDto> getTeamList(@RequestParam String tournamentId,
                                                     @RequestParam(required = false) Integer page){
        return this.teamService.getTeamInfoTournamentResult(tournamentId, page);
    }

}
