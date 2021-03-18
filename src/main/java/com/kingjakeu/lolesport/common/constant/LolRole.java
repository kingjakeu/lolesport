package com.kingjakeu.lolesport.common.constant;

import lombok.Getter;

@Getter
public enum LolRole {
    TOP("Top Laner", "top"),
    JUG("Jungler", "jungle"),
    MID("Mid Laner", "mid"),
    BOT("Bot Laner", "bottom"),
    SUP("Support", "support"),
    COH("Coach", "coach"),
    NF("Not Found", "not-found")
    ;
    private final String fullName;
    private final String slugName;

    LolRole(String fullName, String slugName){
        this.fullName = fullName;
        this.slugName = slugName;
    }

    public static LolRole findByFullName(String fullName){
        for(LolRole lolRole : values()){
            if(lolRole.fullName.equals(fullName)){
                return lolRole;
            }
        }
        return NF;
    }

    public static LolRole findBySlugName(String slugName){
        for(LolRole lolRole : values()){
            if(lolRole.slugName.equals(slugName)){
                return lolRole;
            }
        }
        return NF;
    }
}
