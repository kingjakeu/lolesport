package com.kingjakeu.lolesport.api.pick.dao;

import com.kingjakeu.lolesport.api.pick.domain.PickHistory;
import com.kingjakeu.lolesport.api.pick.domain.PickHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickHistoryRepository extends JpaRepository<PickHistory, PickHistoryId> {
}
