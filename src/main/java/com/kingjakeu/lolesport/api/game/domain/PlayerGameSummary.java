package com.kingjakeu.lolesport.api.game.domain;

import com.kingjakeu.lolesport.api.champion.domain.Champion;
import com.kingjakeu.lolesport.api.player.domain.Player;
import com.kingjakeu.lolesport.common.constant.LolRole;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLYR_GAME_SMRY")
public class PlayerGameSummary {

    @Setter
    @EmbeddedId
    private PlayerGameSummaryId playerGameSummaryId;

    @Setter
    @MapsId("gameId")
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @Setter
    @MapsId("playerId")
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "LANE_ROLE", length = 5)
    private LolRole role;

    @Setter
    @Column(name = "SIDE", length = 5)
    private String side;

    @Setter
    @ManyToOne
    @JoinColumn(name = "CHAMP_ID")
    private Champion champion;

    @Column(name = "CHAMP_LEVEL")
    private Long champLevel;

    @ElementCollection
    @Column(name = "ITEMS")
    private List<String> items;

    @ElementCollection
    @Column(name = "PAR_RUNES")
    private List<String> parentRunes;

    @ElementCollection
    @Column(name = "SUB_RUNES")
    private List<String> subRunes;

    @ElementCollection
    @Column(name = "STAT_RUNES")
    private List<String> statRunes;

    @Column(name = "KILLS")
    private Long kill;

    @Column(name = "DEATHS")
    private Long death;

    @Column(name = "ASSISTS")
    private Long assist;

    @Column(name = "TOTAL_DEAL_DMG")
    private Long totalDamageDealt;

    @Column(name = "TOTAL_CHAMP_DEAL_DMG")
    private Long totalDamageDealtToChampion;

    @Column(name = "TOTAL_DMG_TAKEN")
    private Long totalDamageTaken;

    @Column(name = "TOTAL_HEAL")
    private Long totalHeal;

    @Column(name = "OBJECT_DMG")
    private Long damageDealtToObject;

    @Column(name = "TURRET_DMG")
    private Long damageDealtToTurret;

    @Column(name = "TOTAL_GOLD")
    private Long totalGoldEarned;

    @Column(name = "TOTAL_CS")
    private Long totalMinionKilled;

    @Column(name = "TURRET_KILL")
    private Long turretKill;

    @Column(name = "TOTAL_JUG_CS")
    private Long totalJungleMinionKilled;

    @Column(name = "TEAM_JUG_CS")
    private Long teamJungleMinionKilled;

    @Column(name = "OPP_JUG_CS")
    private Long enemyJungleMinionKilled;

    @Column(name = "V_WARD_BUY")
    private Long visionWardBuyCount;

    @Column(name = "S_WARD_BUY")
    private Long sightWardBuyCount;

    @Column(name = "WARD_PLACE")
    private Long wardPlaced;

    @Column(name = "WARD_KILL")
    private Long wardKilled;

    @Column(name = "VISION_SCORE")
    private Long visionScore;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;
}
