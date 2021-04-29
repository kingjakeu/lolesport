package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.PlayerGameSummary;
import com.kingjakeu.promode.api.game.dto.PlayerStatDto;
import com.kingjakeu.promode.api.player.domain.Player;
import com.kingjakeu.promode.common.constant.LolRole;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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

import java.util.List;
import java.util.Objects;

import static com.kingjakeu.promode.api.game.domain.QPlayerGameSummary.playerGameSummary;
import static com.kingjakeu.promode.api.player.domain.QPlayer.player;
import static com.kingjakeu.promode.api.game.domain.QGame.game;
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

    public Page<PlayerStatDto> findPlayerAverageSummary(LolRole role, Pageable pageable){
        JPAQuery<PlayerStatDto> query = this.jpaQueryFactory
                .select(Projections.constructor(PlayerStatDto.class,
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
                .where(this.eqRole(role))
                .orderBy(playerGameSummary.player.summonerName.asc());

        JPQLQuery<PlayerStatDto> pagination = querydsl().applyPagination(pageable, query);

        long totalCount = pagination.fetchCount();
        Pageable pageRequest = this.revisePageRequest(pageable, totalCount);
        return new PageImpl<>(querydsl().applyPagination(pageRequest, query).fetch(), pageRequest, totalCount);
    }

    public List<PlayerStatDto> findPlayerStatDtoList(String tournamentId, String matchBlock, LolRole lolRole){
        JPAQuery<PlayerStatDto> query = this.jpaQueryFactory
                .select(Projections.constructor(PlayerStatDto.class,
                        player.id,
                        player.summonerName,
                        player.role,
                        playerGameSummary.kill.sum(),
                        playerGameSummary.death.sum(),
                        playerGameSummary.assist.sum(),
                        player.team.id
                                .when(game.winTeam.id).then(1L).otherwise(Long.valueOf(0)).sum(),
                        playerGameSummary.playerGameSummaryId.gameId.count()
                        )
                ).from(playerGameSummary)
                .innerJoin(playerGameSummary.player, player)
                .innerJoin(playerGameSummary.game, game)
                .where(game.tournament.id.eq(tournamentId), game.match.blockName.eq(matchBlock), player.role.eq(lolRole))
                .groupBy(player.id);
        return query.fetch();
    }

    private BooleanExpression eqRole(LolRole role){
        if(role == null) return null;
        return playerGameSummary.role.eq(role);
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
