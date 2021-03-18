package com.kingjakeu.lolesport.api.info.dao;

import com.kingjakeu.lolesport.api.info.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByCode(String code);
    Optional<Team> findByName(String name);
}
