package com.kingjakeu.promode.api.crawl.dto.team;

import com.kingjakeu.promode.api.player.domain.Player;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.common.constant.LolRole;
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
                .englishName(this.firstName.stripTrailing() + " " + this.lastName.stripTrailing())
                .team(team)
                .role(LolRole.findBySlugName(this.role))
                .imageUrl(this.image)
                .build();
    }
}
