package com.kingjakeu.lolesport.api.info.dto.team;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class TeamDataDto {
    private ArrayList<TeamDto> teams;
}
