package com.kingjakeu.lolesport.api.info.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleEventDto {
    private String blockName;
    private String startTime;
    private String state;
    private String type;
    private ScheduleLeagueDto league;
    private ScheduleMatchDto match;

}
