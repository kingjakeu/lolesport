package com.kingjakeu.lolesport.api.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingjakeu.lolesport.api.info.domain.Match;
import com.kingjakeu.lolesport.api.info.dto.LolEsportDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDataDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleEventDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleTeamDto;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import com.kingjakeu.lolesport.common.util.HttpRequester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchInfoService {

    public void crawlLeagueSchedule(String leagueId){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("hl", "ko-KR");
        parameters.put("leagueId", leagueId);

        String result = HttpRequester.doGet(
                CrawlUrl.LCK_SCHEDULE_LIST.getUrl(),
                CrawlUrl.LCK_SCHEDULE_LIST.getHttpHeader(),
                parameters
        );

        ObjectMapper objectMapper = new ObjectMapper();
        LolEsportDataDto<ScheduleDataDto> resultDto = null;
        try {
            resultDto = objectMapper.readValue(result, new TypeReference<>() {});
            ScheduleDto scheduleDto = resultDto.getData()
                    .getSchedule();

            List<Match> matches = new ArrayList<>();
            for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
                matches.add(eventDto.toMatchEntity());
            }

            while (scheduleDto.getPages().getOlder() != null){
                parameters.put("pageToken", scheduleDto.getPages().getOlder());

                result = HttpRequester.doGet(
                        CrawlUrl.LCK_SCHEDULE_LIST.getUrl(),
                        CrawlUrl.LCK_SCHEDULE_LIST.getHttpHeader(),
                        parameters
                );

                resultDto = objectMapper.readValue(result, new TypeReference<>() {});

                scheduleDto = resultDto.getData()
                        .getSchedule();

                for(ScheduleEventDto eventDto : scheduleDto.getEvents()){
                    matches.add(eventDto.toMatchEntity());
                }
            }

            for(Match match : matches){
                System.out.println(match.toString());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
