package com.kingjakeu.promode.api.rune.dao;

import com.kingjakeu.promode.api.rune.domain.Rune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuneRepository extends JpaRepository<Rune, String> {
}
