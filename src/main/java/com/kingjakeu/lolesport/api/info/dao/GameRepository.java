package com.kingjakeu.lolesport.api.info.dao;

import com.kingjakeu.lolesport.api.info.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {
}
