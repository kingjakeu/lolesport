package com.kingjakeu.promode.api.tournament.dao;

import com.kingjakeu.promode.api.tournament.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, String> {
}
