package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingjakeu.lolesport.api.info.dao.LeagueRepository;
import com.kingjakeu.lolesport.api.info.dao.TeamRepository;
import com.kingjakeu.lolesport.api.info.domain.League;
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

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamInfoService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    public void crawlLckTeams(){
        try {
            Document doc = Crawler.doGetDocument(CrawlUrl.LCK_TEAM_LIST.getUrl());
            Elements roasterHeaders = doc.getElementsByClass("tournament-roster-header");
            for(Element header : roasterHeaders){
                Elements headerLinks = header.getElementsByTag("a");
                for(Element link : headerLinks){
                    Team team = Team.builder()
                            .name(link.text())
                            //.league("LCK")
                            .build();
                    this.teamRepository.save(team);
                }
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void crawlTeams(String leagueName) throws JsonProcessingException {
        Optional<League> leagueOptional = this.leagueRepository.findByName(leagueName);
        if(leagueOptional.isEmpty()) return;

        final League league = leagueOptional.get();

        List<TeamDto> teamDtoList = this.crawlRawAllTeams();
        for(TeamDto teamDto : teamDtoList){
            if(teamDto.isActiveTeam() && teamDto.leagueNameEqualsTo(league.getName())){
                this.teamRepository.save(teamDto.toTeamEntity(league));
            }
        }
    }

    public List<TeamDto> crawlRawAllTeams() throws JsonProcessingException {
        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        LolEsportDataDto<TeamDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.TEAM_INFO_LIST, parameters, new TypeReference<>() {});
        return resultDto.getData()
                .getTeams();
    }
}
