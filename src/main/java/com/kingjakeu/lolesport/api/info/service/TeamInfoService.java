package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingjakeu.lolesport.api.info.dao.TeamRepository;
import com.kingjakeu.lolesport.api.info.domain.Team;
import com.kingjakeu.lolesport.api.info.dto.LolEsportDataDto;
import com.kingjakeu.lolesport.api.info.dto.team.TeamDataDto;
import com.kingjakeu.lolesport.api.info.dto.team.TeamDto;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.Crawler;
import com.kingjakeu.lolesport.common.util.HttpRequester;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Document doc = Crawler.doGet(CrawlUrl.LCK_TEAM_LIST.getUrl());
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

    public void newCrawl() throws JsonProcessingException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("hl", "ko-KR");
        String result = HttpRequester.doGet(
                CrawlUrl.TEAM_INFO_LIST.getUrl(),
                CrawlUrl.TEAM_INFO_LIST.getHttpHeader(),
                parameters
        );
        LolEsportDataDto<TeamDataDto> resultDto = new ObjectMapper().readValue(result, new TypeReference<>() {});
        ArrayList<TeamDto> teamDtoList = resultDto.getData().getTeams();
        List<TeamDto> qualifiedTeams = new ArrayList<>();
        for(TeamDto teamDto : teamDtoList){
            if(teamDto.isLckTeam()){
                qualifiedTeams.add(teamDto);
            }
        }
        System.out.println("DONE");
    }
}
