package com.kingjakeu.lolesport.api.crawl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.champion.dao.ChampionRepository;
import com.kingjakeu.lolesport.api.config.service.ConfigService;
import com.kingjakeu.lolesport.api.crawl.dto.champion.ChampionDataDto;
import com.kingjakeu.lolesport.api.crawl.dto.item.ItemDataDto;
import com.kingjakeu.lolesport.api.crawl.dto.rune.ParentRuneDto;
import com.kingjakeu.lolesport.api.item.dao.ItemRepository;
import com.kingjakeu.lolesport.api.rune.dao.RuneRepository;
import com.kingjakeu.lolesport.common.constant.CrawlUrlConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GameComponentCrawlService {

    private final CrawlCommonService crawlCommonService;
    private final ConfigService configService;

    private final ChampionRepository championRepository;
    private final ItemRepository itemRepository;
    private final RuneRepository runeRepository;

    public void crawlChampionData() {
        String url = this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_BASE.name());
        url = url.replace("{patch-version}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_CHAMPION_PATCH.name()));
        url = url.replace("{component}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_CHAMPION.name()));
        url = url.replace("{language-nation}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_KOR.name()));

        ChampionDataDto dataDto = this.crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});
        this.championRepository.saveAll(dataDto.toChampionEntities());
    }

    public void crawlItemData(){
        String url = this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_BASE.name());
        url = url.replace("{patch-version}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_ITEM_PATCH.name()));
        url = url.replace("{component}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_ITEM.name()));
        url = url.replace("{language-nation}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_KOR.name()));

        ItemDataDto dataDto = this.crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});
        this.itemRepository.saveAll(dataDto.toItemEntities());
    }

    public void crawlRuneData(){
        String url = this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_BASE.name());
        url = url.replace("{patch-version}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_RUNE_PATCH.name()));
        url = url.replace("{component}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_RUNE.name()));
        url = url.replace("{language-nation}", this.configService.findConfigValue(CrawlUrlConfig.GAME_COMPONENT_KOR.name()));

        ParentRuneDto[] dataDto = this.crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});
        for(ParentRuneDto runeDto : dataDto){
            this.runeRepository.saveAll(runeDto.toRuneEntities());
        }
    }


}
