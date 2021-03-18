package com.kingjakeu.lolesport.api.info.dto.standing;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class StandDto {
    private ArrayList<StandStageDto> stages;
}
