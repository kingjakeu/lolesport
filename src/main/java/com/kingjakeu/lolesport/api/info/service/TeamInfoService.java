package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.info.dao.LeagueRepository;
import com.kingjakeu.lolesport.api.info.dao.PlayerRepository;
import com.kingjakeu.lolesport.api.info.dao.TeamRepository;
import com.kingjakeu.lolesport.api.info.domain.League;
import com.kingjakeu.lolesport.api.info.domain.Team;
import com.kingjakeu.lolesport.api.info.dto.LolEsportDataDto;
import com.kingjakeu.lolesport.api.info.dto.team.TeamDataDto;
import com.kingjakeu.lolesport.api.info.dto.team.TeamDto;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.exception.ResourceNotFoundException;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamInfoService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final PlayerRepository playerRepository;

    public void crawlLckTeamsCrawlKeyword(){
        Document doc = Crawler.doGetDocument(CrawlUrl.LCK_TEAM_LIST.getUrl());
        Elements roasterHeaders = doc.getElementsByClass("tournament-roster-header");
        for(Element header : roasterHeaders){
            Elements headerLinks = header.getElementsByTag("a");
            Attributes attributes = headerLinks.get(0).attributes();

            String teamName = attributes.get("data-to-titles").split("\\|\\|")[0];

            Team team = this.findTeamByName(teamName);
            team.setCrawlKey(attributes.get("href"));

            this.teamRepository.save(team);
        }
    }
    private Team findTeamByName(String teamName) {
        Optional<Team> teamOptional = this.teamRepository.findByName(teamName);
        if(teamOptional.isEmpty()) throw new ResourceNotFoundException(CommonError.TEAM_INFO_NOT_FOUND);
        return teamOptional.get();
    }

    public void crawlTeamsAndPlayers(String leagueName) {
        Optional<League> leagueOptional = this.leagueRepository.findByName(leagueName);
        if(leagueOptional.isEmpty()) return;

        final League league = leagueOptional.get();

        List<TeamDto> teamDtoList = this.crawlRawAllTeams();
        for(TeamDto teamDto : teamDtoList){
            if(teamDto.isActiveTeam() && teamDto.leagueNameEqualsTo(league.getName())){
                Team team = this.teamRepository.save(teamDto.toTeamEntity(league));
                this.playerRepository.saveAll(teamDto.toPlayerEntities(team));
            }
        }
    }

    public List<TeamDto> crawlRawAllTeams() {
        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        LolEsportDataDto<TeamDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.TEAM_INFO_LIST, parameters, new TypeReference<>() {});
        return resultDto.getData()
                .getTeams();
    }
}
