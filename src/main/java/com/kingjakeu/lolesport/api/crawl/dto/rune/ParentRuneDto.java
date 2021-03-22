package com.kingjakeu.lolesport.api.crawl.dto.rune;

import com.kingjakeu.lolesport.api.rune.domain.Rune;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ParentRuneDto {
    private Long id;
    private String key;
    private String icon;
    private String name;
    private RuneSlotDto[] slots;

    public List<Rune> toRuneEntities(){
        List<Rune> runeList = new ArrayList<>();
        for(RuneSlotDto runeSlotDto : slots){
            runeList.addAll(runeSlotDto.toRunEntities());
        }
        return runeList;
    }
}
