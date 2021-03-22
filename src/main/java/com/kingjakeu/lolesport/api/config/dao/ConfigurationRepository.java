package com.kingjakeu.lolesport.api.config.dao;

import com.kingjakeu.lolesport.api.config.domain.InternalConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<InternalConfig, String> {
}
