package com.kingjakeu.promode.api.config.service;

import com.kingjakeu.promode.api.config.dao.ConfigurationRepository;
import com.kingjakeu.promode.api.config.domain.InternalConfig;
import com.kingjakeu.promode.common.constant.CommonError;
import com.kingjakeu.promode.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigurationRepository configurationRepository;

    public void saveConfig(String key, String value){
        this.configurationRepository.save(
                InternalConfig.builder()
                .id(key)
                .value(value)
                .build()
        );
    }

    public String findConfigValue(String key){
        Optional<InternalConfig> optionalInternalConfig = this.configurationRepository.findById(key);
        if(optionalInternalConfig.isEmpty()) throw new ResourceNotFoundException(CommonError.CONFIG_NOT_FOUND);
        return optionalInternalConfig.get().getValue();
    }

}
