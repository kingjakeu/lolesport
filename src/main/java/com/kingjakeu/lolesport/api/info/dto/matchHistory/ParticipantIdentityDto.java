package com.kingjakeu.lolesport.api.info.dto.matchHistory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@NoArgsConstructor
public class ParticipantIdentityDto {
    private Long participantId;
    private ParticipantPlayerDto player;
}
