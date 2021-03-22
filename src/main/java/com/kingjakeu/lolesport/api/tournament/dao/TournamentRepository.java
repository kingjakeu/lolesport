package com.kingjakeu.lolesport.api.tournament.dao;

import com.kingjakeu.lolesport.api.tournament.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, String> {
}
