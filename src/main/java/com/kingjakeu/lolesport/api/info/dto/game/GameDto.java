package com.kingjakeu.lolesport.api.info.dto.game;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class GameDto {
    private Long number;
    private String id;
    private String state;
    private ArrayList<GameTeamDto> teams;
    private ArrayList<GameVodDto> vods;

    public GameTeamDto getBlueTeam(){
        for(GameTeamDto gameTeamDto : teams){
            if(gameTeamDto.getSide().equals("blue")){
                return gameTeamDto;
            }
        }
        return null;
    }

    public GameTeamDto getRedTeam(){
        for(GameTeamDto gameTeamDto : teams){
            if(gameTeamDto.getSide().equals("red")){
                return gameTeamDto;
            }
        }
        return null;
    }

    public LocalDateTime getStartTime(){
        return vods.get(0) == null ? null : ZonedDateTime.parse(vods.get(0).getFirstFrameTime()).toLocalDateTime();
    }

    public Long getStartMillis(){
        return vods.get(0) == null ? null : vods.get(0).getStartMillis();
    }

    public Long getEndMillis(){
        return vods.get(0) == null ? null : vods.get(0).getEndMillis();
    }

}
