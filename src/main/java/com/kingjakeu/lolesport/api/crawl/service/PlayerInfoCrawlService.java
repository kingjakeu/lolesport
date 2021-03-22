package com.kingjakeu.lolesport.api.crawl.service;

import com.kingjakeu.lolesport.api.player.dao.PlayerRepository;
import com.kingjakeu.lolesport.api.player.domain.Player;
import com.kingjakeu.lolesport.common.constant.CrawlUrlConfig;
import com.kingjakeu.lolesport.common.constant.DateTimeFormat;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerInfoCrawlService {

    private final CrawlCommonService crawlCommonService;
    private final PlayerRepository playerRepository;

    public void crawlLckPlayersDetail(){
        List<Player> playerList = this.crawlLckPlayersCrawlKeyword();
        this.crawlPlayersDetailsInfo(playerList);
    }

    private List<Player> crawlLckPlayersCrawlKeyword() {
        Document doc = crawlCommonService.crawlDocument(CrawlUrlConfig.LCK_TEAM_LIST);
        Elements playerElements = doc.getElementsByClass("tournament-roster-player");

        List<Player> playerList = new ArrayList<>();
        for(Element playerElement : playerElements){
            Elements playerLinks = playerElement.getElementsByTag("a");
            for(Element playerLink : playerLinks){
                String summonerName = playerLink.text();
                Optional<Player> playerOptional = this.playerRepository.findBySummonerName(summonerName);

                if(playerOptional.isPresent()){
                    Player player = playerOptional.get();
                    player.setCrawlKey(playerLink.attr("href"));
                    playerList.add(this.playerRepository.save(player));
                }
            }
        }
        return playerList;
    }

    private void crawlPlayersDetailsInfo(List<Player> playerList) {
        for(Player player : playerList){
            if(player.getCrawlKey() != null){
                Document doc = this.crawlCommonService.crawlDocument(CrawlUrlConfig.LOL_GAMEPEDIA, player.getCrawlKey());
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
}
