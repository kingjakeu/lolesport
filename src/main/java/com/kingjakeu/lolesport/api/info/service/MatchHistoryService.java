package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.lolesport.api.info.dao.GameRepository;
import com.kingjakeu.lolesport.api.info.domain.Game;
import com.kingjakeu.lolesport.api.info.dto.matchHistory.GameDto;
import com.kingjakeu.lolesport.api.info.dto.timeline.TimeLineDto;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.Crawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchHistoryService {

    private GameRepository gameRepository;

    public void crawlMatchHistory(String url) throws JsonProcessingException {
        GameDto gameDto = Crawler.doGetObject(url, CrawlUrl.acsMatchHistoryHeader(), Collections.emptyMap(), new TypeReference<GameDto>() {});
        System.out.println("DONE");
    }

    public void crawlGameTimeLine(String url) throws JsonProcessingException{
        TimeLineDto timeLineDto = Crawler.doGetObject(url, CrawlUrl.acsMatchHistoryHeader(), Collections.emptyMap(), new TypeReference<TimeLineDto>() {});
        System.out.println("DONE");
    }

    public void crawlGameMatchHistory(String gameId) throws Exception {
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if(optionalGame.isEmpty()) throw new Exception("ERROR");
    }
}
