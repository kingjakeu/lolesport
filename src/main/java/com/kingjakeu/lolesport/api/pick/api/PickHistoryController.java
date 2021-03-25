package com.kingjakeu.lolesport.api.pick.api;

import com.kingjakeu.lolesport.api.pick.dto.ChampPickInfoDto;
import com.kingjakeu.lolesport.api.pick.service.PickHistoryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/pick")
@RequiredArgsConstructor
public class PickHistoryController {

    private final PickHistoryInfoService pickHistoryInfoService;

    @GetMapping("/tournament/{tournamentId}/team/{teamId}/most")
    public Map<String, ChampPickInfoDto> getMostPickInTournamentByTeam(@PathVariable String tournamentId,
                                                                       @PathVariable String teamId){
        return this.pickHistoryInfoService.findMostPickByTeamInTournament(tournamentId, teamId);
    }
}
