package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.config.dao.ConfigurationRepository;
import com.kingjakeu.lolesport.api.config.domain.Configuration;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrawlCommonService {
    private final ConfigurationRepository configurationRepository;

    public <T> T crawlAcsMatchHistory(String url, TypeReference<T> returnType) {
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", this.findConfigValue("ACS_MATCH_HISTORY_COOKIE"));

        return Crawler.doGetObject(url, header, Collections.emptyMap(), returnType);
    }

    private String findConfigValue(String configKey) {
        Optional<Configuration> optionalConfiguration = this.configurationRepository.findById(configKey);
        if(optionalConfiguration.isEmpty()) throw new ResourceNotFoundException(CommonError.CONFIG_NOT_FOUND);
        return optionalConfiguration.get().getValue();
    }
}
