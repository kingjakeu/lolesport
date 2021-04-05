package com.kingjakeu.promode.api.ban.dto.response;

import com.kingjakeu.promode.api.ban.dto.ChampBanInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class TournamentTeamChampBanResDto {
    private final String tournamentId;
    private final String teamId;
    private final List<ChampBanInfoDto> bannedChampList;
}
