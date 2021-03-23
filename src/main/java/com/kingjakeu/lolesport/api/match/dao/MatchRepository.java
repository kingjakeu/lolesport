package com.kingjakeu.lolesport.api.match.dao;

import com.kingjakeu.lolesport.api.match.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, String> {
    List<Match> findAllByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Match> findAllByBlockName(String blockName);
}
