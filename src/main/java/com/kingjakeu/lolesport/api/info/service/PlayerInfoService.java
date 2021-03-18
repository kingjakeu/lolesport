package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.info.dao.PlayerRepository;
import com.kingjakeu.lolesport.api.info.domain.Player;
import com.kingjakeu.lolesport.api.info.dto.LolEsportDataDto;
import com.kingjakeu.lolesport.api.info.dto.team.TeamDataDto;
import com.kingjakeu.lolesport.api.info.dto.team.TeamDto;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.constant.DateTimeFormat;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerInfoService {

    private final PlayerRepository playerRepository;

    public void crawlLckPlayersCrawlKeyword() throws Exception{
        Document doc = Crawler.doGetDocument(CrawlUrl.LCK_TEAM_LIST.getUrl());
        Elements playerElements = doc.getElementsByClass("tournament-roster-player");

        for(Element playerElement : playerElements){
            Elements playerLinks = playerElement.getElementsByTag("a");
            for(Element playerLink : playerLinks){
                String summonerName = playerLink.text();
                Optional<Player> playerOptional = this.playerRepository.findBySummonerName(summonerName);

                if(playerOptional.isPresent()){
                    Player player = playerOptional.get();
                    player.setCrawlKey(playerLink.attr("href"));
                    this.playerRepository.save(player);
                }
            }
        }
    }

    public void crawlPlayersDetailsInfo() throws IOException {
        List<Player> playerList = this.playerRepository.findAll();
        for(Player player : playerList){
            if(player.getCrawlKey() != null){
                Document doc = Crawler.doGetDocument(CrawlUrl.LOL_GAMEPEDIA.getUrl() + player.getCrawlKey());
                Element infoboxPlayer = doc.getElementById("infoboxPlayer");
                Elements otherInfos = infoboxPlayer.getElementsByTag("td");
                String birthdayStr = otherInfos.get(6).text().split("\\(")[0];

                player.setName(otherInfos.get(2).text().split("\\(")[1].replace(")",""));
                player.setBirthDay(LocalDate.parse(birthdayStr, DateTimeFormat.PLAYER_BIRTHDAY));
                player.setNationality(otherInfos.get(4).text());
                playerRepository.save(player);
            }
        }
    }

    public void crawlPlayersByTeam(String teamId) throws JsonProcessingException {
        Map<String, String> parameters = Crawler.createCommonLolEsportParameters();
        parameters.put("id", teamId);
        LolEsportDataDto<TeamDataDto> resultDto = Crawler.doGetObject(
                CrawlUrl.TEAM_INFO_LIST, parameters, new TypeReference<>() {});

        List<TeamDto> teamDtoList = resultDto.getData()
                .getTeams();

        for(TeamDto teamDto : teamDtoList){
            //this.playerRepository.saveAll(teamDto.toPlayerEntities());
        }
    }
}
