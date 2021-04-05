package com.kingjakeu.promode.common.constant;

import lombok.Getter;

@Getter
public enum LolLeague {

    LCK("LCK", "lck"),


    NF("NF", "not found")
    ;
    private final String name;
    private final String slug;

    LolLeague(String name, String slug){
        this.name = name;
        this.slug = slug;
    }

    public static LolLeague findByName(String name){
        for(LolLeague lolLeague : values()){
            if(lolLeague.getName().equals(name)){
                return lolLeague;
            }
        }
        return NF;
    }
}
