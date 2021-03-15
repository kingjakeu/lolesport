package com.kingjakeu.lolesport.api.info.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class ScheduleTeamDto {
    private String code;
    private String image;
    private String name;
    private Map<String, Object> record;
    private Map<String, Object> result;

}
