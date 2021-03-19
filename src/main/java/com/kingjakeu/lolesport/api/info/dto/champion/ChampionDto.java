package com.kingjakeu.lolesport.api.info.dto.champion;

import com.kingjakeu.lolesport.api.info.domain.Champion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Getter
@NoArgsConstructor
public class ChampionDto {
    private String id;
    private String key;
    private String name;
    private String title;
    private String partype;
    private String version;
    private String blurb;
    private LinkedHashMap<String, Object> image;
    private LinkedHashMap<String, Object> stats;
    private LinkedHashMap<String, Object> info;
    private String[] tags;

    public Champion toChampionEntity(){
        return Champion.builder()
                .id(this.id)
                .name(this.name)
                .crawlKey(this.key)
                .patchVersion(this.version)
                .build();
    }
}
