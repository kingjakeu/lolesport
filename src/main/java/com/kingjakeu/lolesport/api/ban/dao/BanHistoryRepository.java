package com.kingjakeu.lolesport.api.ban.dao;

import com.kingjakeu.lolesport.api.ban.domain.BanHistory;
import com.kingjakeu.lolesport.api.ban.dto.ChampBanInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BanHistoryRepository extends JpaRepository<BanHistory, Long> {
    List<BanHistory> findAllByPatchVersion(String patchVersion);

    @Query("select new com.kingjakeu.lolesport.api.ban.dto.ChampBanInfoDto(" +
            "bh.bannedChampion.id, " +
            "bh.bannedChampion.name, " +
            "count(bh) as banCount)" +
            " from BanHistory bh" +
            " where bh.game.tournament.id = :tournamentId " +
            " and (bh.game.blueTeam.id = :teamId or bh.game.redTeam.id = :teamId)" +
            " group by bh.bannedChampion.id"
    )
    Page<ChampBanInfoDto> findChampBanInfoInTournamentByTeamId(@Param("tournamentId") String tournamentId,
                                                               @Param("teamId") String teamId,
                                                               Pageable pageable);
}
