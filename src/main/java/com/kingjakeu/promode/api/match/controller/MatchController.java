package com.kingjakeu.promode.api.match.controller;

import com.kingjakeu.promode.api.match.dto.response.MatchResultResDto;
import com.kingjakeu.promode.api.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
