package com.kingjakeu.lolesport.common.constant;

import lombok.Getter;

@Getter
public enum LolRole {
    TOP("Top Laner"),
    JUG("Jungler"),
    MID("Mid Laner"),
    BOT("Bot Laner"),
    SUP("Support"),
    COH("Coach"),
    NF("Not Found")
    ;
    private String fullName;

    LolRole(String fullName){
        this.fullName = fullName;
    }

    public static LolRole findByFullName(String fullName){
        for(LolRole lolRole : values()){
            if(lolRole.fullName.equals(fullName)){
                return lolRole;
            }
        }
        return NF;
    }
}
