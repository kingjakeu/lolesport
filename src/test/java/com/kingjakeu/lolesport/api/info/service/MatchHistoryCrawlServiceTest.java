package com.kingjakeu.lolesport.api.info.service;

import com.kingjakeu.lolesport.api.crawl.service.MatchHistoryCrawlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchHistoryCrawlServiceTest {

    @Autowired
    private MatchHistoryCrawlService matchHistoryCrawlService;

    @Test
    void crawlGameTimeLine() {
        matchHistoryCrawlService.crawlGameTimeLine("https://acs.leagueoflegends.com/v1/stats/game/ESPORTSTMNT01/1896701/timeline?gameHash=b5f757080fbc9aed");
    }

    @Test
    void crawlGameMatchHistory() {
        try {
            this.matchHistoryCrawlService.crawlGameMatchHistory("105522984812916176");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}