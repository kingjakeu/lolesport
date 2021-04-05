package com.kingjakeu.promode.api.crawl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.promode.api.config.service.ConfigService;
import com.kingjakeu.promode.common.constant.CrawlUrlConfig;
import com.kingjakeu.promode.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CrawlCommonService {

    private final ConfigService configService;

    public <T> T crawlAcsMatchHistory(String url, TypeReference<T> returnType) {
        Map<String, String> header = new LinkedHashMap<>();
        header.put("Cookie", this.configService.findConfigValue("ACS_MATCH_HISTORY_COOKIE"));

        return Crawler.doGetObject(url, header, Collections.emptyMap(), returnType);
    }

    public <T> T crawlLolEsportApi(CrawlUrlConfig crawlUrlConfig, Map<String, String> parameters, TypeReference<T> returnType){
        Map<String, String> header = new HashMap<>();
        header.put("x-api-key", this.configService.findConfigValue("LOL_ESPORT_X_API_KEY"));

        String url = this.configService.findConfigValue(crawlUrlConfig.name());
        return Crawler.doGetObject(url, header, parameters, returnType);
    }

    public Document crawlDocument(CrawlUrlConfig crawlUrlConfig){
        String url = this.configService.findConfigValue(crawlUrlConfig.name());
        return Crawler.doGetDocument(url);
    }

    public Document crawlDocument(CrawlUrlConfig crawlUrlConfig, String addString){
        String url = this.configService.findConfigValue(crawlUrlConfig.name()) + addString;
        return Crawler.doGetDocument(url);
    }

    public Map<String, String> createCommonLolEsportParameters(){
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("hl", "ko-KR");
        return parameters;
    }
}
