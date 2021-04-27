package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.TeamGameSummary;
import com.kingjakeu.promode.api.team.dto.TeamTournamentResultDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.criterion.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.kingjakeu.promode.api.game.domain.QTeamGameSummary.teamGameSummary;
import static com.kingjakeu.promode.api.team.domain.QTeam.team;

@Repository
public class TeamGameSummaryRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public TeamGameSummaryRepositorySupport(JPAQueryFactory jpaQueryFactory){
        super(TeamGameSummary.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<TeamTournamentResultDto> findTeamTournamentResultDto(String tournamentId, Pageable pageable){
        JPAQuery<TeamTournamentResultDto> query = this.jpaQueryFactory
                .select(Projections.constructor(TeamTournamentResultDto.class,
                        team,
                        teamGameSummary.win.when(true).then(1L).otherwise(Long.valueOf(0)).sum(),
                        teamGameSummary.teamGameSummaryId.gameId.count()
                ))
                .from(teamGameSummary)
                .innerJoin(teamGameSummary.team, team)
                .where(teamGameSummary.game.tournament.id.eq(tournamentId));

        JPQLQuery<TeamTournamentResultDto> pagination = querydsl().applyPagination(pageable, query);
        long totalCount = pagination.fetchCount();
        Pageable pageRequest = this.revisePageRequest(pageable, totalCount);
        return new PageImpl<>(querydsl().applyPagination(pageRequest, query).fetch(), pageRequest, totalCount);
    }

    private Pageable revisePageRequest(Pageable pageable, long totalCount){
        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return totalCount > (long) (pageNo - 1) * pageSize
                ? pageable
                : PageRequest.of((int)Math.ceil((double)totalCount / pageNo), pageSize);
    }

    private Querydsl querydsl(){
        return Objects.requireNonNull(getQuerydsl());
    }
}
