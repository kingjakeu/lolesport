package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.config.dao.ConfigurationRepository;
import com.kingjakeu.lolesport.api.config.domain.Configuration;
import com.kingjakeu.lolesport.api.info.dao.ChampionRepository;
import com.kingjakeu.lolesport.api.info.dto.champion.ChampionDataDto;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameComponentService {

    private final CrawlCommonService crawlCommonService;
    private final ConfigurationRepository configurationRepository;

    private final ChampionRepository championRepository;

    public void crawlChampionData() {
        String url = this.findConfig("GAME_COMPONENT_BASE").getValue();
        url = url.replace("{patch-version}", this.findConfig("GAME_COMPONENT_CHAMPION_PATCH").getValue());
        url = url.replace("{component}", this.findConfig("GAME_COMPONENT_CHAMPION").getValue());
        url = url.replace("{language-nation}", this.findConfig("GAME_COMPONENT_KOR").getValue());

        ChampionDataDto dataDto = this.crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});
        this.championRepository.saveAll(dataDto.toChampionEntities());
    }
    
    private Configuration findConfig(String key) {
        Optional<Configuration> configurationOptional = this.configurationRepository.findById(key);
        if(configurationOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.CONFIG_NOT_FOUND);
        return configurationOptional.get();
    }
}
