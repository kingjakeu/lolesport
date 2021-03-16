package com.kingjakeu.lolesport.api.info.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LolEsportDataDto<T> {
    private T data;
}
