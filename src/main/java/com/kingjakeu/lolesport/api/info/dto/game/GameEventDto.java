package com.kingjakeu.lolesport.api.info.dto.game;

import com.kingjakeu.lolesport.api.info.domain.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GameEventDto {
    private String id;
    private String type;
    private GameTournamentDto tournament;
    private GameLeagueDto league;
    private GameMatchDto match;
    private ArrayList<Object> streams;

    public List<Game> toGameEntities(){
        List<Game> games = new ArrayList<>();
        for(GameDto gameDto : this.match.getGames()){
            games.add(Game.builder()
                    .id(gameDto.getId())
                    .matchId(this.id)
                    .tournamentId(this.tournament.getId())
                    .leagueCode(this.league.getName())
                    .number(gameDto.getNumber())
                    .state(gameDto.getState())
                    .blueTeam(gameDto.getBlueTeam().getId())
                    .redTeam(gameDto.getRedTeam().getId())
                    .startTime(gameDto.getStartTime())
                    .startMillis(gameDto.getStartMillis())
                    .endMillis(gameDto.getEndMillis())
                    .build()
            );
        }
        return games;
    }
}
