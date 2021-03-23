package com.kingjakeu.lolesport.api.game.dao;

import com.kingjakeu.lolesport.api.game.domain.TeamGameSummary;
import com.kingjakeu.lolesport.api.game.domain.TeamGameSummaryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamGameSummaryRepository extends JpaRepository<TeamGameSummary, TeamGameSummaryId> {
}
