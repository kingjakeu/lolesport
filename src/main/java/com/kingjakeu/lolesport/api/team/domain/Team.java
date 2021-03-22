package com.kingjakeu.lolesport.api.team.domain;

import com.kingjakeu.lolesport.api.league.domain.League;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TEAM_INFO")
public class Team {
    @Id
    @Column(name = "ID", length = 20)
    private String id;

    @Column(name = "CODE", nullable = false, length = 5)
    private String code;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "SLUG", nullable = false, length = 100)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @Lob
    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Setter
    @Column(name = "CRAWL_KEY", length = 100)
    private String crawlKey;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;

    public boolean crawlKeyEqualsTo(String crawlKey){
        return this.crawlKey.equals(crawlKey);
    }
}
