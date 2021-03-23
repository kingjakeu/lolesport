package com.kingjakeu.lolesport.api.crawl.dto.matchhistory;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private Map<Long, String> participantIdMap = new HashMap<>();

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

    public String findSummonerNameById(Long id){
        if(this.participantIdMap.isEmpty()){
            for(ParticipantIdentityDto identityDto : this.participantIdentities){
                this.participantIdMap.put(identityDto.getParticipantId(), identityDto.getPlayer().getRefinedSummonerName());
            }
        }
        return this.participantIdMap.get(id);
    }
}
