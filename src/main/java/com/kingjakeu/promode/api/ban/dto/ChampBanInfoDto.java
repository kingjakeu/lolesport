package com.kingjakeu.promode.api.ban.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ChampBanInfoDto {
    private String championId;
    private String championName;
    private Long banCount;
}
