package com.kingjakeu.lolesport.api.crawl.dto.timeline;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipantDto {
    private Long participantId;
    private Long level;
    private Long currentGold;
    private Long dominionScore;
    private Long jungleMinionsKilled;
    private Long minionsKilled;
    private FramePositionDto position;
    private Long teamScore;
    private Long totalGold;
    private Long xp;
}
