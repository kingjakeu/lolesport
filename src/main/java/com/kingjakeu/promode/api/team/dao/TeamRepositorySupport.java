package com.kingjakeu.promode.api.team.dao;

import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.api.team.dto.TeamTournamentResultDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.kingjakeu.promode.api.standing.domain.QStanding.standing;
import static com.kingjakeu.promode.api.team.domain.QTeam.team;

@Repository
public class TeamRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public TeamRepositorySupport(JPAQueryFactory jpaQueryFactory){
        super(Team.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
     * 팀 목록 + 토너 먼트 결과 조회
     * @param tournamentId 토너먼트 아이디
     * @param pageable 페이지
     * @return 팀목록
     */
    public Page<TeamTournamentResultDto> findTeamTournamentResultDto(String tournamentId, Pageable pageable){
        JPAQuery<TeamTournamentResultDto> query = this.jpaQueryFactory
                .select(Projections.constructor(
                        TeamTournamentResultDto.class,
                        team,
                        standing
                        )
                ).from(team)
                .join(standing)
                .on(team.id.eq(standing.team.id))
                .where(standing.tournament.id.eq(tournamentId))
                .orderBy(standing.rank.asc());

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
