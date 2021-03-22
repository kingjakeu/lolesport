package com.kingjakeu.lolesport.common.constant;

import lombok.Getter;

@Getter
public enum CommonError {

    CRAWL_ERROR("CRL0001", "Error while crawling"),
    CRAWL_MAP_ERROR("CRL0002", "Error while mapping crawled value"),

    GAME_INFO_NOT_FOUND("GME0001", "Game not found"),
    GAME_MATCH_INFO_NOT_FOUND("GME0002", "Game match history is empty"),

    TOURNAMENT_INFO_NOT_FOUND("TRN0001", "tournament not found"),

    LEAGUE_INFO_NOT_FOUND("LGE0001", "League info not found"),

    CONFIG_NOT_FOUND("CFG0001", "Config not found"),

    CHAMP_NOT_FOUND("CHP0001", "champion not found"),

    TEAM_INFO_NOT_FOUND("TEM0001", "Team info not found")

    ;
    private final String code;
    private final String message;

    CommonError(String code, String message){
        this.code = code;
        this.message = message;
    }
}
