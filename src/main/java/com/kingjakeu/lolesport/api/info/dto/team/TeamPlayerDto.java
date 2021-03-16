package com.kingjakeu.lolesport.api.info.dto.team;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamPlayerDto {
    private String id;
    private String summonerName;
    private String firstName;
    private String lastName;
    private String image;
    private String role;
}
