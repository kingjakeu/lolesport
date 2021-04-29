package com.kingjakeu.promode.api.player.controller;

import com.kingjakeu.promode.api.game.dto.PlayerStatDto;
import com.kingjakeu.promode.api.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/summary")
    public List<PlayerStatDto> getPlayerStatList(@RequestParam(required = false) String role,
                                                    @RequestParam(required = false) Integer pageIdx){
        return this.playerService.getPlayerSummaryList(role, pageIdx);
    }

    @GetMapping("/best")
    public List<PlayerStatDto> getBestPlayerStatList(@RequestParam String tournamentId,
                                                        @RequestParam String weekBlock){
        return this.playerService.getBestPlayerStatList(tournamentId, weekBlock);
    }
}
