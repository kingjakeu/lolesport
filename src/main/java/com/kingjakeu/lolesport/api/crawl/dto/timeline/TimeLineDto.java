package com.kingjakeu.lolesport.api.crawl.dto.timeline;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class TimeLineDto {
    private Long frameInterval;
    private ArrayList<TimeLineFrameDto> frames;
}
