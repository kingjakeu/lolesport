package com.kingjakeu.lolesport.api.crawl.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class MatchHistoryRequestDto {
    private String gameId;
    private String matchId;
}
