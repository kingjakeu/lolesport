package com.kingjakeu.promode.api.crawl.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchHistoryRequestDto {
    private String gameId;
    private String matchId;
}
