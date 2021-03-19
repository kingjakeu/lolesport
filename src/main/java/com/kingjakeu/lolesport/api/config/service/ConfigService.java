package com.kingjakeu.lolesport.api.config.service;

import com.kingjakeu.lolesport.api.config.dao.ConfigurationRepository;
import com.kingjakeu.lolesport.api.config.domain.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigurationRepository configurationRepository;

    public void saveConfig(String key, String value){
        this.configurationRepository.save(
                Configuration.builder()
                .id(key)
                .value(value)
                .build()
        );
    }

}
