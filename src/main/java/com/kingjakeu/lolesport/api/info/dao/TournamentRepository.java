package com.kingjakeu.lolesport.api.info.dao;

import com.kingjakeu.lolesport.api.info.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, String> {
}
