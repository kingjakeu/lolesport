package com.kingjakeu.lolesport.api.info.service;

import com.kingjakeu.lolesport.api.info.dao.TeamRepository;
import com.kingjakeu.lolesport.api.info.domain.Team;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TeamInfoService {
    private final TeamRepository teamRepository;

    public Team createTeam(){
        Team team = Team.builder()
                .name("jake")
                .league("LCK")
                .build();
        return this.teamRepository.save(team);
    }

    public void crawlLckTeams(){
        try {
            Document doc = Crawler.doGet(CrawlUrl.LCK_TEAM_LIST);
            Elements roasterHeaders = doc.getElementsByClass("tournament-roster-header");
            for(Element header : roasterHeaders){
                Elements headerLinks = header.getElementsByTag("a");
                for(Element link : headerLinks){
                    Team team = Team.builder()
                            .name(link.text())
                            .league("LCK")
                            .build();
                    this.teamRepository.save(team);
                }
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
