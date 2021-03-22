package com.kingjakeu.lolesport.api.crawl.service;

import com.kingjakeu.lolesport.api.crawl.service.GameComponentCrawlService;
import com.kingjakeu.lolesport.api.game.dao.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameComponentCrawlServiceTest {

    @Autowired
    GameComponentCrawlService gameComponentCrawlService;

    @Autowired
    GameRepository gameRepository;
    @Test
    void crawlChampionData() {
        this.gameComponentCrawlService.crawlChampionData();
    }

    @Test
    void crawlItemData(){
        this.gameComponentCrawlService.crawlItemData();
    }

    @Test
    void crawlRuneData() {
        this.gameComponentCrawlService.crawlRuneData();;
    }
}
