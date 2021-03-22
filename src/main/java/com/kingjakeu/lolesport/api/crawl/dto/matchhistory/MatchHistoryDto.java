package com.kingjakeu.lolesport.api.crawl.dto.matchhistory;

import lombok.*;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@ToString
public class MatchHistoryDto {
    private Long gameId;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;
    private Long queueId;
    private Long mapId;
    private Long seasonId;
    private String gameVersion;
    private String gameMode;
    private String gameType;
    private ArrayList<TeamDto> teams;
    private ArrayList<ParticipantDto> participants;
    private ArrayList<ParticipantIdentityDto> participantIdentities;

    public TeamDto getBlueTeamDto(){
        for (TeamDto teamDto : this.teams){
            if(teamDto.isBlueTeam()) return teamDto;
        }
        return null;
    }

    public TeamDto getRedTeamDto(){
        for(TeamDto teamDto : this.teams){
            if(teamDto.isRedTeam()) return teamDto;
        }
        return null;
    }
}
