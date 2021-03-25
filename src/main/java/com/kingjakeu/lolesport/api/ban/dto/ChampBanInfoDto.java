package com.kingjakeu.lolesport.api.ban.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChampBanInfoDto {
    private String championId;
    private String championName;
    private Long banCount;
}
