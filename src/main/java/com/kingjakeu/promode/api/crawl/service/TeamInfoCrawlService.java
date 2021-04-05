package com.kingjakeu.promode.api.crawl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.promode.api.player.dao.PlayerRepository;
import com.kingjakeu.promode.api.team.dao.TeamRepository;
import com.kingjakeu.promode.api.league.domain.League;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.api.crawl.dto.LolEsportDataDto;
import com.kingjakeu.promode.api.crawl.dto.team.TeamDataDto;
import com.kingjakeu.promode.api.crawl.dto.team.TeamDto;
import com.kingjakeu.promode.api.league.service.LeagueCommonService;
import com.kingjakeu.promode.api.team.service.TeamCommonService;
import com.kingjakeu.promode.common.constant.CrawlUrlConfig;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamInfoCrawlService {

    private final LeagueCommonService leagueCommonService;

    private final TeamCommonService teamCommonService;
    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;
    private final CrawlCommonService crawlCommonService;

    /**
     * 팀 + 플레이어 기본 정보 크롤링
     * @param leagueName 리그 이름 ex. LCK
     */
    public void crawlTeamsAndPlayers(String leagueName) {
        final League league = this.leagueCommonService.findLeagueByName(leagueName);
        List<TeamDto> teamDtoList = this.crawlRawAllTeams();
        for(TeamDto teamDto : teamDtoList){
            if(teamDto.isActiveTeam() && teamDto.leagueNameEqualsTo(league.getName())){
                Team team = this.teamRepository.save(teamDto.toTeamEntity(league));
                this.playerRepository.saveAll(teamDto.toPlayerEntities(team));
            }
        }
    }

    /**
     * 팀 정보 전체 가져 오기
     * @return 전체 팀 정보
     */
    private List<TeamDto> crawlRawAllTeams() {
        Map<String, String> parameters = this.crawlCommonService.createCommonLolEsportParameters();
        LolEsportDataDto<TeamDataDto> resultDto = this.crawlCommonService.crawlLolEsportApi(
                CrawlUrlConfig.TEAM_INFO_LIST, parameters, new TypeReference<>() {});
        return resultDto.getData()
                .getTeams();
    }


    /**
     * lck 팀 디테일 정보 크롤링
     */
    public void crawlLckTeamsDetail(){
        Document doc = this.crawlCommonService.crawlDocument(CrawlUrlConfig.LCK_TEAM_LIST);
        Elements roasterHeaders = doc.getElementsByClass("tournament-roster-header");
        for(Element header : roasterHeaders){
            Elements headerLinks = header.getElementsByTag("a");
            Attributes attributes = headerLinks.get(0).attributes();

            String teamName = attributes.get("data-to-titles").split("\\|\\|")[0];

            Team team = this.teamCommonService.findTeamByName(teamName);
            team.setCrawlKey(attributes.get("href"));

            this.teamRepository.save(team);
        }
    }
}
