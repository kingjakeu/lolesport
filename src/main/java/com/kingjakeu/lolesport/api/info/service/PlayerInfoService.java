package com.kingjakeu.lolesport.api.info.service;

import com.kingjakeu.lolesport.api.info.dao.PlayerRepository;
import com.kingjakeu.lolesport.api.info.domain.Player;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.constant.DateTimeFormat;
import com.kingjakeu.lolesport.common.constant.LolRole;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PlayerInfoService {

    private final PlayerRepository playerRepository;

    public void crawlLckPlayers(){
        try{
            List<String> linkList = new ArrayList<>();
            Document doc = Crawler.doGet(CrawlUrl.LCK_TEAM_LIST);
            Elements playerElements = doc.getElementsByClass("tournament-roster-player");

            for(Element playerElement : playerElements){
                Elements playerLinks = playerElement.getElementsByTag("a");
                for(Element playerLink : playerLinks){
                    linkList.add(playerLink.attr("href"));
                }
            }

            for(String link : linkList){
                this.crawlPlayer(link);
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void crawlPlayer(String link){
        try {
            Document doc = Crawler.doGet(CrawlUrl.LOL_GAMEPEDIA + link);
            Element infoboxPlayer = doc.getElementById("infoboxPlayer");

            // maybe only one title info box
            Elements infoBoxTitle = infoboxPlayer.getElementsByClass("infobox-title");
            String playName = infoBoxTitle.get(0).text();

            Elements otherInfos = infoboxPlayer.getElementsByTag("td");
            String birthdayStr = otherInfos.get(6).text().split("\\(")[0];

            Player player = Player.builder()
                    .playName(playName)
                    .name(otherInfos.get(2).text())
                    .nationality(otherInfos.get(4).text())
                    .birthDay(LocalDate.parse(birthdayStr, DateTimeFormat.PLAYER_BIRTHDAY))
                    .teamName(otherInfos.get(10).text().substring(2))
                    .role(LolRole.findByFullName(otherInfos.get(14).text()))
                    .build();

            playerRepository.save(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
