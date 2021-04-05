package com.kingjakeu.promode.api.game.dao;

import com.kingjakeu.promode.api.game.domain.PlayerGameSummary;
import com.kingjakeu.promode.api.game.domain.PlayerGameSummaryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerGameSummaryRepository extends JpaRepository<PlayerGameSummary, PlayerGameSummaryId> {
}
