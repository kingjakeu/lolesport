package com.kingjakeu.lolesport.api.ban.dao;

import com.kingjakeu.lolesport.api.ban.domain.BanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanHistoryRepository extends JpaRepository<BanHistory, Long> {
}
