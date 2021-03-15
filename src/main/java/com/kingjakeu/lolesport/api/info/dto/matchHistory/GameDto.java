package com.kingjakeu.lolesport.api.info.dto.matchHistory;

import lombok.*;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@ToString
public class GameDto {
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
}
