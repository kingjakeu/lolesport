package com.kingjakeu.lolesport.api.info.domain;

import com.kingjakeu.lolesport.common.constant.LolRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLAYER_INFO")
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PLAY_NAME")
    private String playName;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTHDAY")
    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private LolRole role;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Builder
    public Player(String playName, String name, LocalDate birthDay, LolRole role, String teamName, String nationality){
        this.playName = playName;
        this.name = name;
        this.birthDay = birthDay;
        this.role = role;
        this.teamName = teamName;
        this.nationality = nationality;
    }

}
