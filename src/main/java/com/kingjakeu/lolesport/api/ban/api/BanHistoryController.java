package com.kingjakeu.lolesport.api.ban.api;

import com.kingjakeu.lolesport.api.ban.dto.response.TournamentTeamChampBanResDto;
import com.kingjakeu.lolesport.api.ban.service.BanHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/ban")
@RequiredArgsConstructor
public class BanHistoryController {

    private final BanHistoryService banHistoryService;

    @GetMapping("/tournament/{tournamentId}/team/{teamId}/most")
    public TournamentTeamChampBanResDto getMostBanInTournamentByTeam(@PathVariable String tournamentId,
                                                                     @PathVariable String teamId){
        return this.banHistoryService.findMostBanByTeamInTournament(tournamentId, teamId);
    }
}
