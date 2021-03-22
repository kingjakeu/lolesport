package com.kingjakeu.lolesport.api.crawl.dto.standing;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class StandStageDto {
    private String name;
    private String slug;
    private String type;
    private ArrayList<StandSectionDto> sections;
}
