package com.kingjakeu.lolesport.api.info.domain;

import com.kingjakeu.lolesport.common.constant.LolRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLAYER_INFO")
public class Player {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SUMMONER_NAME")
    private String summonerName;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ENG_NAME")
    private String englishName;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private LolRole role;

    @Column(name = "TEAM_ID")
    private String teamId;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

}
