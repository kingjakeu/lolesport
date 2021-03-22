package com.kingjakeu.lolesport.api.crawl.dto.timeline;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class FrameEventDto {
    private Long participantId;
    private Long timestamp;
    private String type;

    // SKILL LEVEL UP
    private String levelUpType;
    private Long skillSlot;

    // ITEM_PURCHASED & ITEM_DESTROYED & ITEM_SOLD
    private Long itemId;

    // ITEM_UNDO
    private Long beforeId;
    private Long afterId;

    // WARD_PLACED
    private Long creatorId;

    //WARD_PLACED & WARD_KILL
    private String wardType;

    // WARD_KILL & CHAMPION_KILL & ELITE_MONSTER_KILL
    private Long killerId;

    // CHAMPION_KILL && BUILDING_KILL
    private ArrayList<Long> assistingParticipantIds;
    private FramePositionDto position;

    // CHAMPION_KILL
    private Long victimId;

    // ELITE_MONSTER_KILL
    private String monsterType;
    private String monsterSubType;

    // BUILDING_KILL
    private String buildingType;
    private String laneType;
    private String towerType;
    private Long teamId;
}
