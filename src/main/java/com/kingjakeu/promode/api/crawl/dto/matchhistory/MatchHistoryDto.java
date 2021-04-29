package com.kingjakeu.promode.api.crawl.dto.matchhistory;

import com.kingjakeu.promode.common.constant.CommonCode;
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

    private Map<Long, String> participantIdMap;

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

    public String getWinTeamSide(){
        if(this.getBlueTeamDto().isWinTeam()) {
            return CommonCode.BLUE_SIDE.getCode();
        }else if(this.getRedTeamDto().isWinTeam()){
            return CommonCode.RED_SIDE.getCode();
        }
        return null;
    }

    public String findSummonerNameById(Long id){
        if(this.participantIdMap == null){
            this.participantIdMap = new HashMap<>();
            for(ParticipantIdentityDto identityDto : this.participantIdentities){
                this.participantIdMap.put(identityDto.getParticipantId(), identityDto.getPlayer().getRefinedSummonerName());
            }
        }
        return this.participantIdMap.get(id);
    }

    public String getGameVersion(){
        String[] str = this.gameVersion.split("\\.");
        if(str.length < 3) return this.gameVersion;
        return str[0] + "." + str[1];
    }
}
