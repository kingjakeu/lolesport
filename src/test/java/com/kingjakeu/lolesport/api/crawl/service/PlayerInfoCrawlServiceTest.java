package com.kingjakeu.lolesport.api.crawl.service;

import com.kingjakeu.lolesport.api.crawl.service.PlayerInfoCrawlService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlayerInfoCrawlServiceTest {

    @Autowired
    private PlayerInfoCrawlService playerInfoCrawlService;
}