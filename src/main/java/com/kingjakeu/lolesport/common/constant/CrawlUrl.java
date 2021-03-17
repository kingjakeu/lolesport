package com.kingjakeu.lolesport.common.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Getter
public enum  CrawlUrl {
    LOL_GAMEPEDIA("https://lol.gamepedia.com"),
    LCK_TEAM_LIST("https://lol.gamepedia.com/LCK/2021_Season/Spring_Season"),
    LCK_MATCH_HISTORY_LIST("https://lol.gamepedia.com/LCK/2021_Season/Spring_Season/Match_History"),

    LEAGUE_SCHEDULE_LIST(
            "https://esports-api.lolesports.com/persisted/gw/getSchedule",
            CrawlUrl.lolEsportHeader()
    ),

    TEAM_INFO_LIST(
            "https://esports-api.lolesports.com/persisted/gw/getTeams",
            CrawlUrl.lolEsportHeader()
    ),

    MATCH_EVENT_DETAIL(
            "https://esports-api.lolesports.com/persisted/gw/getEventDetails",
            CrawlUrl.lolEsportHeader()
    ),

    ;
    private final String url;
    private Map<String, String> httpHeader;

    CrawlUrl(String url){
        this.url = url;
    }
    CrawlUrl(String url, Map<String, String> httpHeader){
        this.url = url;
        this.httpHeader = httpHeader;
    }

    protected static Map<String, String> lolEsportHeader(){
        Map<String, String> header = new HashMap<>();
        header.put("x-api-key", "0TvQnueqKa5mxJntVWt0w4LpLfEkrV1Ta8rQBb9Z");
        return header;
    }

    public static Map<String, String> acsMatchHistoryHeader(){
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "id_token=eyJraWQiOiJzMSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI1MDZmMzlmMC02MWVlLTU3NGEtYTE4YS0wYjAwYzE5MTNmMDYiLCJjb3VudHJ5Ijoia29yIiwicGxheWVyX3Bsb2NhbGUiOiJrby1LUiIsImFtciI6WyJwYXNzd29yZCJdLCJpc3MiOiJodHRwczpcL1wvYXV0aC5yaW90Z2FtZXMuY29tIiwibG9sIjpbeyJjdWlkIjo0NzQ0ODAwLCJjcGlkIjoiS1IiLCJ1aWQiOjQ3NDQ4MDAsInVuYW1lIjoiamFrZWhzdSIsInB0cmlkIjpudWxsLCJwaWQiOiJLUiIsInN0YXRlIjoiRU5BQkxFRCJ9XSwibG9jYWxlIjoiZW5fVVMiLCJhdWQiOiJyc28td2ViLWNsaWVudC1wcm9kIiwiYWNyIjoidXJuOnJpb3Q6YnJvbnplIiwicGxheWVyX2xvY2FsZSI6ImtvLUtSIiwiZXhwIjoxNjE0OTI5Mjg3LCJpYXQiOjE2MTQ4NDI4ODcsImFjY3QiOnsiZ2FtZV9uYW1lIjoi7ISc64yA66y46rWs7LqQ66as66i47IugIiwidGFnX2xpbmUiOiJLUjEifSwianRpIjoibzBVNzJOSzZqV0EiLCJsb2dpbl9jb3VudHJ5Ijoia29yIn0.ho-ri8As1cur6UjefjRyXDjdLNpkSRDNCmINXr2yITxPkmlU-Ox_OFxiYU1hI84pRXdZxANgZqSZCR59d4qW9_Q4cTVd6fKXxptFB89RbCou3A4s5SSMu6kR1i-EFIn-cm6JFtX_1b2zZPd8HWnbImaqIj9S8dNKdiWVnOOCoyA");
        return header;
    }
}
