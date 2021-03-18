package com.kingjakeu.lolesport.api.info.dto.schedule;

import com.kingjakeu.lolesport.api.info.domain.League;
import com.kingjakeu.lolesport.api.info.domain.Match;
import com.kingjakeu.lolesport.common.constant.CommonCode;
import com.kingjakeu.lolesport.common.constant.LolLeague;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@ToString
public class ScheduleEventDto {
    private String blockName;
    private String startTime;
    private String state;
    private String type;
    private ScheduleLeagueDto league;
    private ScheduleMatchDto match;

    public boolean isNotInProgress(){
        return !CommonCode.STATE_IN_PROGRESS.codeEqualsTo(this.state);
    }

    public Match toMatchEntity(League league){
        return Match.builder()
                .id(this.match.getId())
                .blockName(this.blockName)
                .league(league)
                .team1(this.getMatch().getTeams().get(0).toTeamEntity())
                .team2(this.getMatch().getTeams().get(1).toTeamEntity())
                .startTime(ZonedDateTime.parse(this.startTime).toLocalDateTime())
                .state(this.state)
                .build();
    }
}
