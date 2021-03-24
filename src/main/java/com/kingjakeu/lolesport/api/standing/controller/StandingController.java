package com.kingjakeu.lolesport.api.standing.controller;

import com.kingjakeu.lolesport.api.standing.dto.response.TournamentStandingResDto;
import com.kingjakeu.lolesport.api.standing.service.StandingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/standing")
@RequiredArgsConstructor
public class StandingController {

    private final StandingInfoService standingInfoService;
    
    @GetMapping
    public TournamentStandingResDto getStanding(@RequestParam String tournamentId){
        return standingInfoService.getStandingByTournament(tournamentId);
    }
}
