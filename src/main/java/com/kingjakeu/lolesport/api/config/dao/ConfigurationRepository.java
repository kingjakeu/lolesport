package com.kingjakeu.lolesport.api.config.dao;

import com.kingjakeu.lolesport.api.config.domain.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, String> {
}
