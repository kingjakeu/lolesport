package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.PlayerGameSummary;
import com.kingjakeu.promode.api.game.dto.PlayerAverageSummaryDto;
import com.kingjakeu.promode.common.constant.LolRole;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kingjakeu.promode.api.game.domain.QPlayerGameSummary.playerGameSummary;
import static com.kingjakeu.promode.api.player.domain.QPlayer.player;
import static com.kingjakeu.promode.api.game.domain.QTeamGameSummary.teamGameSummary;

@Repository
public class PlayerGameSummaryRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public PlayerGameSummaryRepositorySupport(JPAQueryFactory jpaQueryFactory){
        super(PlayerGameSummary.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Long countTeamDeathByGame(String gameId, String side){
        return this.jpaQueryFactory
                .select(playerGameSummary.death.sum())
                .from(playerGameSummary)
                .where(playerGameSummary.game.id.eq(gameId), playerGameSummary.side.eq(side))
                .fetchOne();
    }

    public List<PlayerAverageSummaryDto> findPlayerAverageSummary(LolRole role){
        return this.jpaQueryFactory
                .select(Projections.constructor(PlayerAverageSummaryDto.class,
                        playerGameSummary.playerGameSummaryId.playerId,
                        playerGameSummary.player.summonerName,
                        playerGameSummary.role,
                        playerGameSummary.kill.avg(),
                        playerGameSummary.death.avg(),
                        playerGameSummary.assist.avg(),
                        teamGameSummary.win.when(true).then(1L).otherwise(Long.valueOf(0)).sum(),
                        playerGameSummary.playerGameSummaryId.gameId.count()
                )).from(playerGameSummary)
                .innerJoin(playerGameSummary.player, player)
                .join(teamGameSummary)
                .on(playerGameSummary.playerGameSummaryId.gameId.eq(teamGameSummary.teamGameSummaryId.gameId),
                        playerGameSummary.side.eq(teamGameSummary.side))
                .groupBy(playerGameSummary.playerGameSummaryId.playerId)
                .where(playerGameSummary.role.eq(role))
                .orderBy(playerGameSummary.player.summonerName.asc())
                .limit(10)
                .fetch();
    }
}
