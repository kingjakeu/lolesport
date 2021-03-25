package com.kingjakeu.lolesport.api.ban.service;

import com.kingjakeu.lolesport.api.ban.dao.BanHistoryRepository;
import com.kingjakeu.lolesport.api.ban.dto.ChampBanInfoDto;
import com.kingjakeu.lolesport.api.ban.dto.response.TournamentTeamChampBanResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanHistoryService {

    private final BanHistoryRepository banHistoryRepository;

    /**
     * 팀별 토너먼트 Most(5) 밴 챔피언 정보
     * @param tournamentId 토너먼트 아이디
     * @param teamId 팀 아이디
     * @return 밴 챔피언 정보
     */
    public TournamentTeamChampBanResDto findMostBanByTeamInTournament(String tournamentId, String teamId){
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "banCount"));
        Page<ChampBanInfoDto> champBanInfoDtoPage = this.banHistoryRepository.findChampBanInfoInTournamentByTeamId(
                tournamentId, teamId, pageable
        );

        return TournamentTeamChampBanResDto.builder()
                .tournamentId(tournamentId)
                .teamId(teamId)
                .bannedChampList(champBanInfoDtoPage.getContent())
                .build();
    }
}
