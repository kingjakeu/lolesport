package com.kingjakeu.promode.api.crawl.service;

import com.kingjakeu.promode.api.team.dao.TeamRepository;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.api.team.service.TeamCommonService;
import com.kingjakeu.promode.common.util.ImageDownloader;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TeamInfoCrawlServiceTest {

    @Autowired
    private TeamInfoCrawlService teamInfoCrawlService;

    @Autowired
    private MatchInfoCrawlService matchInfoCrawlService;

    @Autowired
    private TeamRepository teamRepository;

    @Order(1)
    @Test
    void crawlTeams() {
        this.matchInfoCrawlService.crawlLeagueInfos();
        this.teamInfoCrawlService.crawlTeamsAndPlayers("LCK");
    }

    @Order(2)
    @Test
    void crawlLckTeams() {
        this.teamInfoCrawlService.crawlLckTeamsDetail();
    }

    @Test
    void downLoadTeamImageIcon(){
        List<Team> teamList = this.teamRepository.findAll();
        String prefix = "https://am-a.akamaihd.net/image?resize=48:&f=";
        for(Team team : teamList){
            try {
                ImageDownloader.download(
                    prefix + team.getImageUrl(),
                        "/Users/hanati-hsyoo/git/promode-api/src/img/"+team.getSlug(),
                        "png"
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}