package com.kingjakeu.promode.api.match.controller;

import com.kingjakeu.promode.api.match.dto.response.MatchGameResultResDto;
import com.kingjakeu.promode.api.match.dto.response.MatchResultResDto;
import com.kingjakeu.promode.api.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public List<MatchResultResDto> getMatchList(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam LocalDate matchDate) {
        return this.matchService.getMatch(matchDate);
    }

    @GetMapping("/{matchId}/games")
    public List<MatchGameResultResDto> getMatchGames(@PathVariable String matchId){
        return this.matchService.getMatchGames(matchId);
    }
}
