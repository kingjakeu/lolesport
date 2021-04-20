package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.PlayerGameSummary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.kingjakeu.promode.api.game.domain.QPlayerGameSummary.playerGameSummary;

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
}
