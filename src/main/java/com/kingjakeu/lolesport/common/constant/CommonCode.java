package com.kingjakeu.lolesport.common.constant;

import lombok.Getter;

@Getter
public enum CommonCode {
    TEAM_STATUS_ACTIVE("active"),
    STATE_IN_PROGRESS("inProgress"),
    ;
    private final String code;
    CommonCode(String code){
        this.code = code;
    }

    public boolean codeEqualsTo(String code){
        return this.code.equals(code);
    }
}
