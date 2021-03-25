package com.kingjakeu.lolesport.api.pick.dao;

import com.kingjakeu.lolesport.api.ban.dto.ChampBanInfoDto;
import com.kingjakeu.lolesport.api.pick.domain.PickHistory;
import com.kingjakeu.lolesport.api.pick.domain.PickHistoryId;
import com.kingjakeu.lolesport.api.pick.dto.ChampPickInfoDto;
import com.kingjakeu.lolesport.common.constant.LolRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PickHistoryRepository extends JpaRepository<PickHistory, PickHistoryId> {

    @Query("select new com.kingjakeu.lolesport.api.pick.dto.ChampPickInfoDto(" +
            "ph.champion.id, ph.champion.name, count(ph) as pickCount)" +
            " from PickHistory ph" +
            " where ph.game.tournament.id = :tournamentId and ph.pickHistoryId.role = :laneRole" +
            " and ( " +
            "   (ph.game.blueTeam.id = :teamId and ph.pickHistoryId.side = :blueFlag) " +
            "   or (ph.game.redTeam.id = :teamId and ph.pickHistoryId.side = :redFlag) " +
            "   ) " +
            "group by ph.champion.id"

    )
    Page<ChampPickInfoDto> findChampPickInfoInTournamentByTeamIdAndLaneRole(@Param("tournamentId") String tournamentId,
                                                                            @Param("teamId") String teamId,
                                                                            @Param("blueFlag") String blueFlag,
                                                                            @Param("redFlag") String redFlag,
                                                                            @Param("laneRole") LolRole laneRole,
                                                                            Pageable pageable);
}
