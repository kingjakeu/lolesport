package com.kingjakeu.lolesport.api.rune.dao;

import com.kingjakeu.lolesport.api.rune.domain.Rune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuneRepository extends JpaRepository<Rune, String> {
}
