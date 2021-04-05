package com.kingjakeu.promode.api.crawl.dto.standing;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Getter
@NoArgsConstructor
public class StandTeamDto {
    private String id;
    private String code;
    private String name;
    private String image;
    private String slug;
    private LinkedHashMap<String, Object> record;
}
