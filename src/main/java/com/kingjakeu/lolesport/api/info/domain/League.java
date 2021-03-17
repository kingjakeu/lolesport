package com.kingjakeu.lolesport.api.info.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LEAGUE_INFO")
public class League {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REGION")
    private String region;

    @Column(name = "IMAGE_URL")
    private String imageUrl;
}

