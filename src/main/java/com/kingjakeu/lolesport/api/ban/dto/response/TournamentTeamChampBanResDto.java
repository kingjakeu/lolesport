package com.kingjakeu.lolesport.api.ban.dto.response;

import com.kingjakeu.lolesport.api.ban.dto.ChampBanInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TournamentTeamChampBanResDto {
    private String tournamentId;
    private String teamId;
    private List<ChampBanInfoDto> bannedChampList;
}
