package com.kingjakeu.lolesport.api.standing.service;

import com.kingjakeu.lolesport.api.game.domain.Game;
import com.kingjakeu.lolesport.api.game.service.GameCommonService;
import com.kingjakeu.lolesport.api.game.service.TeamGameCommonService;
import com.kingjakeu.lolesport.api.match.domain.Match;
import com.kingjakeu.lolesport.api.match.service.MatchCommonService;
import com.kingjakeu.lolesport.api.standing.dao.StandingRepository;
import com.kingjakeu.lolesport.api.standing.domain.Standing;
import com.kingjakeu.lolesport.api.standing.domain.StandingId;
import com.kingjakeu.lolesport.api.team.domain.Team;
import com.kingjakeu.lolesport.api.team.service.TeamCommonService;
import com.kingjakeu.lolesport.api.tournament.domain.Tournament;
import com.kingjakeu.lolesport.api.tournament.service.TournamentCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StandingCommonService {

    private final StandingRepository standingRepository;
    private final MatchCommonService matchCommonService;
    private final GameCommonService gameCommonService;
    private final TournamentCommonService tournamentCommonService;
    private final TeamCommonService teamCommonService;
    private final TeamGameCommonService teamGameCommonService;

    public void calculateStanding(String tournamentId){
        Tournament tournament = this.tournamentCommonService.findTournamentById(tournamentId);
        List<Team> teamList = this.teamCommonService.findAllTeamByLeague(tournament.getLeague());

        Map<StandingId, Standing> standingMap = new HashMap<>();
        for(Team team : teamList){
            StandingId standingId = StandingId.builder().teamId(team.getId()).tournamentId(tournament.getId()).build();
            standingMap.put(standingId, new Standing(standingId, tournament, team));
        }

        List<Match> matchList = this.matchCommonService.findAllByTournament(tournament);
        for(Match match : matchList){
            List<Game> gameList = this.gameCommonService.findCompletedGameByMatch(match);
            if (this.teamGameCommonService.isGameListExisted(gameList)){
                int matchGameCount = gameList.size();
                int team1Win = this.teamGameCommonService.countTeamWinInGameList(match.getTeam1(), gameList);
                int team2Win = this.teamGameCommonService.countTeamWinInGameList(match.getTeam2(), gameList);

                StandingId standingId = StandingId.builder()
                        .teamId(match.getTeam1().getId())
                        .tournamentId(tournament.getId())
                        .build();
                Standing standing = standingMap.get(standingId);
                standing.accumulate(matchGameCount, team1Win);
                standingMap.put(standingId, standing);

                standingId = StandingId.builder()
                        .teamId(match.getTeam2().getId())
                        .tournamentId(tournament.getId())
                        .build();
                standing = standingMap.get(standingId);
                standing.accumulate(matchGameCount, team2Win);
                standingMap.put(standingId, standing);
            }
        }
        List<Standing> standingList = new ArrayList<>(standingMap.values());
        standingList.sort((o1, o2) -> o2.getScore().compareTo(o1.getScore()));
        int i = 0;
        int offset = 1;
        int prev = Integer.MAX_VALUE;
        for(Standing standing : standingList){
            if(standing.getScore() == prev){
                standing.setRank(i);
                offset += 1;
            }else{
                i += offset;
                offset = 1;
                standing.setRank(i);
                prev = standing.getScore();
            }
        }

        this.standingRepository.saveAll(standingList);
    }

}
