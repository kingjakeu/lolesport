package com.kingjakeu.promode.api.player.controller;

import com.kingjakeu.promode.api.game.dto.PlayerAverageSummaryDto;
import com.kingjakeu.promode.api.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/summary")
    public List<PlayerAverageSummaryDto> getPlayerSummaryList(@RequestParam(required = false) String role,
                                                              @RequestParam(required = false)  Integer pageIdx){
        return this.playerService.getPlayerSummaryList(role, pageIdx);
    }
}
