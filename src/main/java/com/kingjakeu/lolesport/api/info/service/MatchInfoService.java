package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingjakeu.lolesport.api.info.dao.MatchRepository;
import com.kingjakeu.lolesport.api.info.domain.Match;
import com.kingjakeu.lolesport.api.info.dto.LolEsportDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleEventDto;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.HttpRequester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchInfoService {

    private final MatchRepository matchRepository;

    private ScheduleDto crawlLeagueSchedule(Map<String, String> parameters) throws JsonProcessingException {
        String result = HttpRequester.doGet(
                CrawlUrl.LEAGUE_SCHEDULE_LIST.getUrl(),
                CrawlUrl.LEAGUE_SCHEDULE_LIST.getHttpHeader(),
                parameters
        );

        LolEsportDataDto<ScheduleDataDto> resultDto =  new ObjectMapper().readValue(result, new TypeReference<>() {});
        return resultDto.getData()
                .getSchedule();
    }

    public void crawlLeagueSchedules(String leagueId) throws JsonProcessingException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("hl", "ko-KR");
        parameters.put("leagueId", leagueId);
        ScheduleDto scheduleDto = this.crawlLeagueSchedule(parameters);

        List<Match> matches = new ArrayList<>();
        for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
            matches.add(eventDto.toMatchEntity());
        }

        while (scheduleDto.getPages().getOlder() != null) {
            parameters.put("pageToken", scheduleDto.getPages().getOlder());
            scheduleDto = this.crawlLeagueSchedule(parameters);
            for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
                matches.add(eventDto.toMatchEntity());
            }
        }
        this.matchRepository.saveAll(matches);
    }
}
