package com.kingjakeu.lolesport.api.info.dto.team;

import com.kingjakeu.lolesport.api.info.domain.Player;
import com.kingjakeu.lolesport.api.info.domain.Team;
import com.kingjakeu.lolesport.common.constant.LolRole;
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

    public Player toPlayerEntity(Team team){
        return Player.builder()
                .id(this.id)
                .summonerName(this.summonerName)
                .englishName(firstName + lastName)
                .team(team)
                .role(LolRole.findBySlugName(this.role))
                .imageUrl(this.image)
                .build();
    }
}
