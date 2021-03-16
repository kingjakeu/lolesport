package com.kingjakeu.lolesport.common.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum  CrawlUrl {
    LOL_GAMEPEDIA("https://lol.gamepedia.com"),
    LCK_TEAM_LIST("https://lol.gamepedia.com/LCK/2021_Season/Spring_Season"),

    LEAGUE_SCHEDULE_LIST(
            "https://esports-api.lolesports.com/persisted/gw/getSchedule",
            HttpHeaders.LOL_ESPORTS_HEADER
    ),

    ;
    private String url;
    private Map<String, String> httpHeader;

    CrawlUrl(String url){
        this.url = url;
    }
    CrawlUrl(String url, Map<String, String> httpHeader){
        this.url = url;
        this.httpHeader = httpHeader;
    }
    protected static class HttpHeaders{
        public static final Map<String, String> LOL_ESPORTS_HEADER = new HashMap<>(){{
            put("x-api-key", "0TvQnueqKa5mxJntVWt0w4LpLfEkrV1Ta8rQBb9Z");
        }};
    }

}
