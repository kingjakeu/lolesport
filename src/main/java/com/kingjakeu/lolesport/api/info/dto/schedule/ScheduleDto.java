package com.kingjakeu.lolesport.api.info.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ScheduleDto {
    private ArrayList<ScheduleEventDto> events;
    private SchedulePageDto pages;
}
