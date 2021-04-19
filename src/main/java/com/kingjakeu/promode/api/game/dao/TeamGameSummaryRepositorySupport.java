package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.Game;
import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.kingjakeu.promode.api.game.dto.GameWinRateDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.kingjakeu.promode.api.game.domain.QTeamGameSummary.teamGameSummary;

@Repository
public class TeamGameSummaryRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public TeamGameSummaryRepositorySupport(JPAQueryFactory jpaQueryFactory){
        super(TeamGameSummary.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
