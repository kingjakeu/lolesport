package com.kingjakeu.promode.api.pick.dao;

import com.kingjakeu.promode.api.champion.domain.Champion;
import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.pick.domain.PickHistory;
import com.kingjakeu.promode.api.pick.dto.ChampPickInfoDto;
import com.kingjakeu.promode.common.constant.LolRole;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kingjakeu.promode.api.game.domain.QTeamGameSummary.teamGameSummary;
import static com.kingjakeu.promode.api.pick.domain.QPickHistory.pickHistory;

@Repository
public class PickHistoryRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public PickHistoryRepositorySupport(JPAQueryFactory jpaQueryFactory){
        super(PickHistory.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<ChampPickInfoDto> findChampPickByLaneAndGameList(LolRole lolRole, List<Game> gameList){
        NumberPath<Long> aliasPickCount = Expressions.numberPath(Long.class, "pickCount");
        return jpaQueryFactory
                .select(Projections.constructor(ChampPickInfoDto.class,
                        pickHistory.champion.id,
                        pickHistory.champion.name,
                        pickHistory.count().as(aliasPickCount)
                )).from(pickHistory)
                .where(pickHistory.pickHistoryId.role.eq(lolRole), pickHistory.game.in(gameList))
                .groupBy(pickHistory.champion)
                .orderBy(aliasPickCount.desc())
                .fetch();
    }

    public Long countGameResultByChampIdAndGameList(String champId, List<Game> gameList, Boolean winFlag){
        return this.jpaQueryFactory
                .select(teamGameSummary.count())
                .from(pickHistory)
                .innerJoin(teamGameSummary)
                .on(pickHistory.game.eq(teamGameSummary.game), pickHistory.pickHistoryId.side.eq(teamGameSummary.side), teamGameSummary.win.eq(winFlag))
                .where(pickHistory.champion.id.eq(champId), pickHistory.game.in(gameList))
                .fetchOne();
    }

    public List<PickHistory> findPickHistoriesByGameIdAndSide(String gameId, String side){
        return this.jpaQueryFactory
                .select(pickHistory)
                .from(pickHistory)
                .where(pickHistory.pickHistoryId.gameId.eq(gameId), pickHistory.pickHistoryId.side.eq(side))
                .fetch();
    }
}
