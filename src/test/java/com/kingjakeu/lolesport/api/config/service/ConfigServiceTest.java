package com.kingjakeu.lolesport.api.config.service;

import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConfigServiceTest {

    @Autowired
    ConfigService configService;

    @Test
    void saveConfig() {
        CrawlUrl[] crawlUrlList = CrawlUrl.values();
        for(CrawlUrl crawlUrl : crawlUrlList){
            configService.saveConfig(crawlUrl.name(), crawlUrl.getUrl());
        }

    }
}