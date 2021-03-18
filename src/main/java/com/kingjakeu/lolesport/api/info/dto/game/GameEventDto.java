package com.kingjakeu.lolesport.api.info.dto.game;

import com.kingjakeu.lolesport.api.info.domain.Game;
import com.kingjakeu.lolesport.api.info.domain.Match;
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
        Match tempMatch = this.toMatchEntity();

        List<Game> games = new ArrayList<>();
        for(GameDto gameDto : this.match.getGames()){
            games.add(Game.builder()
                    .id(gameDto.getId())
                    .match(tempMatch)
                    .tournament(this.tournament.toTournamentEntity())
                    .league(this.league.toLeagueEntity())
                    .number(gameDto.getNumber())
                    .state(gameDto.getState())
                    .blueTeam(gameDto.getBlueTeam().toTeamEntity())
                    .redTeam(gameDto.getRedTeam().toTeamEntity())
                    .startTime(gameDto.getStartTime())
                    .startMillis(gameDto.getStartMillis())
                    .endMillis(gameDto.getEndMillis())
                    .build()
            );
        }
        return games;
    }

    private Match toMatchEntity(){
        return Match.builder().id(this.id).build();
    }
}
