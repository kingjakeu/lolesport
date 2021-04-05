package com.kingjakeu.promode.api.config.dao;

import com.kingjakeu.promode.api.config.domain.InternalConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<InternalConfig, String> {
}
