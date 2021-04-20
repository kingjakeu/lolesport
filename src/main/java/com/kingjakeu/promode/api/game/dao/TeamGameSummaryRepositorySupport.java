package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class TeamGameSummaryRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public TeamGameSummaryRepositorySupport(JPAQueryFactory jpaQueryFactory){
        super(TeamGameSummary.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
