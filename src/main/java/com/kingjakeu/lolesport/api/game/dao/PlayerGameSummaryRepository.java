package com.kingjakeu.lolesport.api.game.dao;

import com.kingjakeu.lolesport.api.game.domain.PlayerGameSummary;
import com.kingjakeu.lolesport.api.game.domain.PlayerGameSummaryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerGameSummaryRepository extends JpaRepository<PlayerGameSummary, PlayerGameSummaryId> {
}
