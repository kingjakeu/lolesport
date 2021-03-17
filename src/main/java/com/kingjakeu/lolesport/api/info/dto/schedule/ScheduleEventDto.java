package com.kingjakeu.lolesport.api.info.dto.schedule;

import com.kingjakeu.lolesport.api.info.domain.Match;
import com.kingjakeu.lolesport.common.constant.LolLeague;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class ScheduleEventDto {
    private String blockName;
    private String startTime;
    private String state;
    private String type;
    private ScheduleLeagueDto league;
    private ScheduleMatchDto match;

    public Match toMatchEntity(){
        return Match.builder()
                .id(this.match.getId())
                .blockName(this.blockName)
                .league(LolLeague.findByName(this.getLeague().getName()))
                .team1(this.getMatch().getTeams().get(0).getCode())
                .team2(this.getMatch().getTeams().get(1).getCode())
                .startTime(ZonedDateTime.parse(this.startTime).toLocalDateTime())
                .state(this.state)
                .build();
    }
}
