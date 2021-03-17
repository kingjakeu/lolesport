package com.kingjakeu.lolesport.api.info.dto.timeline;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class TimeLineDto {
    private Long frameInterval;
    private ArrayList<TimeLineFrameDto> frames;
}
