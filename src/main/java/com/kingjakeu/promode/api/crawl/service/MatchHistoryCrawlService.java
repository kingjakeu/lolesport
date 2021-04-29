package com.kingjakeu.promode.api.crawl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kingjakeu.promode.api.ban.domain.BanHistory;
import com.kingjakeu.promode.api.ban.domain.BanHistoryId;
import com.kingjakeu.promode.api.champion.domain.Champion;
import com.kingjakeu.promode.api.champion.service.ChampionCommonService;
import com.kingjakeu.promode.api.ban.dao.BanHistoryRepository;
import com.kingjakeu.promode.api.config.service.ConfigService;
import com.kingjakeu.promode.api.crawl.dto.matchhistory.ParticipantDto;
import com.kingjakeu.promode.api.crawl.dto.request.MatchHistoryRequestDto;
import com.kingjakeu.promode.api.game.dao.GameRepository;
import com.kingjakeu.promode.api.crawl.dto.matchhistory.MatchHistoryDto;
import com.kingjakeu.promode.api.crawl.dto.matchhistory.TeamDto;
import com.kingjakeu.promode.api.game.dao.PlayerGameSummaryRepository;
import com.kingjakeu.promode.api.game.dao.TeamGameSummaryRepository;
import com.kingjakeu.promode.api.game.domain.*;
import com.kingjakeu.promode.api.game.service.GameCommonService;
import com.kingjakeu.promode.api.match.dao.MatchRepository;
import com.kingjakeu.promode.api.match.domain.Match;
import com.kingjakeu.promode.api.match.service.MatchCommonService;
import com.kingjakeu.promode.api.pick.dao.PickHistoryRepository;
import com.kingjakeu.promode.api.pick.domain.PickHistory;
import com.kingjakeu.promode.api.pick.domain.PickHistoryId;
import com.kingjakeu.promode.api.player.domain.Player;
import com.kingjakeu.promode.api.player.service.PlayerCommonService;
import com.kingjakeu.promode.api.team.domain.Team;
import com.kingjakeu.promode.common.constant.CommonCode;
import com.kingjakeu.promode.common.constant.CommonError;
import com.kingjakeu.promode.common.constant.LolRole;
import com.kingjakeu.promode.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchHistoryCrawlService {

    private final CrawlCommonService crawlCommonService;

    private final BanHistoryRepository banHistoryRepository;
    private final PickHistoryRepository pickHistoryRepository;
    private final GameRepository gameRepository;
    private final MatchRepository matchRepository;

    private final ConfigService configService;
    private final GameCommonService gameCommonService;
    private final MatchCommonService matchCommonService;
    private final ChampionCommonService championCommonService;
    private final PlayerCommonService playerCommonService;
    private final PlayerGameSummaryRepository playerGameSummaryRepository;
    private final TeamGameSummaryRepository teamGameSummaryRepository;


    public void crawlGameTimeLine(String url) {
//        TimeLineDto timeLineDto = Crawler.doGetObject(url, CrawlUrl.acsMatchHistoryHeader(), Collections.emptyMap(), new TypeReference<TimeLineDto>() {});
//        System.out.println("DONE");
    }

    /**
     * 게임 결과 크롤링
     * @param requestDto 크롤링 대상
     */
    public void crawlGameResultDetail(MatchHistoryRequestDto requestDto) {
        if (requestDto.getGameId() != null){
            final Game game = this.gameCommonService.findGameById(requestDto.getGameId());
            this.crawlGameResultDetail(game);
        }else if(requestDto.getMatchId() != null){
            final Match match = this.matchCommonService.findByMatchId(requestDto.getMatchId());
            this.crawlGameResultDetail(match);
        }else{
            throw new ResourceNotFoundException(CommonError.GAME_INFO_NOT_FOUND);
        }
    }


    /**
     * 메치 게임 결과 크롤링
     * @param match 매치 정보
     */
    private void crawlGameResultDetail(Match match) {
        List<Game> gameList = this.gameCommonService.findGameByMatchId(match.getId());

        Map<String, Integer> gameWinnerMap = new HashMap<>();
        gameWinnerMap.put(match.getTeam1().getId(), 0);
        gameWinnerMap.put(match.getTeam2().getId(), 0);

        for(Game game : gameList){
            this.crawlGameResultDetail(game);
            String winnerId = game.getWinTeam().getId();
            gameWinnerMap.put(winnerId, gameWinnerMap.get(winnerId) + 1);
        }

        // 매치 승자 정보 저장
        if(gameWinnerMap.get(match.getTeam1().getId()) > gameWinnerMap.get(match.getTeam2().getId())){
            match.setWinTeam(match.getTeam1());
        }else{
            match.setWinTeam(match.getTeam2());
        }
        this.matchRepository.save(match);
    }

    /**
     * 게임 결과 디테일 크롤링
     * @param game 게임 정보
     */
    private void crawlGameResultDetail(Game game){
        // 매치 히스토리 링크가 아직 크롤링 되지 않았으면, 에러
        if(game.isMatchHistoryLinkEmpty()) throw new ResourceNotFoundException(CommonError.GAME_MATCH_INFO_NOT_FOUND);

        // 게임 결과
        MatchHistoryDto matchHistoryDto = this.crawlMatchHistoryByApi(game.getMatchHistoryUrl());

        // 게임 결과 업데이트
        game.setPatchVersion(matchHistoryDto.getGameVersion());
        game.setWinTeam(game.getTeamBySide(matchHistoryDto.getWinTeamSide()));
        this.gameRepository.save(game);

        // 팀게임 결과
        this.saveTeamGameSummary(game, matchHistoryDto);
        // 밴 로그
        this.saveBanHistory(game, matchHistoryDto);
        // 플레이어 게임 결과
        this.savePlayerGameSummaryAndPickHistory(game, matchHistoryDto);
    }

    /**
     * acs riot match history 정보 크롤링
     * @param matchHistoryUrl 매치 링크
     * @return 경기 결과 정보
     */
    private MatchHistoryDto crawlMatchHistoryByApi(String matchHistoryUrl){
        // refine url
        String matchHistoryPageBaseUrl = this.configService.findConfigValue("MATCH_HISTORY_PAGE_BASE");
        String matchHistoryApiBaseUrl = this.configService.findConfigValue("MATCH_HISTORY_API_BASE");
        String url = matchHistoryUrl.replace(matchHistoryPageBaseUrl, matchHistoryApiBaseUrl);

        //do crawl
        return this.crawlCommonService.crawlAcsMatchHistory(url, new TypeReference<>() {});
    }

    /**
     * 팀 게임 결과 정보 저장
     * @param game 게임
     * @param matchHistoryDto 경기 결과
     */
    public void saveTeamGameSummary(Game game, MatchHistoryDto matchHistoryDto){
        // 블루 팀
        this.saveTeamGameSummary(game, matchHistoryDto, CommonCode.BLUE_SIDE.getCode());
        // 레드 팀
        this.saveTeamGameSummary(game, matchHistoryDto, CommonCode.RED_SIDE.getCode());
    }

    /**
     * 팀 게임 결과 정보 저장 by side
     * @param game 게임
     * @param matchHistoryDto 경기 결과
     * @param side side
     */
    private void saveTeamGameSummary(Game game, MatchHistoryDto matchHistoryDto, String side){
        // 블루 or 레드 팀
        Team team = CommonCode.BLUE_SIDE.codeEqualsTo(side) ? game.getBlueTeam() : game.getRedTeam();

        // 팀 게임 정보 아이디
        TeamGameSummaryId teamGameSummaryId = TeamGameSummaryId.builder()
                .gameId(game.getId())
                .teamId(team.getId())
                .build();

        // 블루 or 레드 정보
        TeamGameSummary teamGameSummary =  CommonCode.BLUE_SIDE.codeEqualsTo(side) ?
                matchHistoryDto.getBlueTeamDto().toTeamGameSummaryEntity()
                : matchHistoryDto.getRedTeamDto().toTeamGameSummaryEntity();

        // 팀 게임 정보 저장
        teamGameSummary.setTeamGameSummaryId(teamGameSummaryId);
        teamGameSummary.setGame(game);
        teamGameSummary.setTeam(team);
        teamGameSummary.setSide(side);
        this.teamGameSummaryRepository.save(teamGameSummary);
    }

    /**
     * 게임 밴 내역 저장
     * @param game 게임
     * @param matchHistoryDto 게임 결과
     */
    public void saveBanHistory(Game game, MatchHistoryDto matchHistoryDto) {
        // 블루 팀
        this.saveBanHistory(game, matchHistoryDto, CommonCode.BLUE_SIDE.getCode());
        // 레드 팀
        this.saveBanHistory(game, matchHistoryDto, CommonCode.RED_SIDE.getCode());
    }

    /**
     * 게임 벤 내역 저장 by side
     * @param game 게임
     * @param matchHistoryDto 게임결과
     * @param side side
     */
    private void saveBanHistory(Game game, MatchHistoryDto matchHistoryDto, String side) {
        //Side 로 팀정보 가져오기
        TeamDto teamDto = CommonCode.BLUE_SIDE.codeEqualsTo(side) ? matchHistoryDto.getBlueTeamDto() : matchHistoryDto.getRedTeamDto();
        List<String> banChampionKeyList = teamDto.getBanChampionKeyList();

        int banTurn = 1;
        for(String champKey : banChampionKeyList){
            // 밴 내역 아이디 생성
            BanHistoryId banHistoryId = BanHistoryId.builder()
                    .gameId(game.getId())
                    .side(side)
                    .banTurn(banTurn++) // 밴 차례 하나씩 증가
                    .build();

            // 밴 내역 저장
            this.banHistoryRepository.save(
                    BanHistory.builder()
                    .banHistoryId(banHistoryId)
                    .game(game)
                    .bannedChampion(this.championCommonService.findById(champKey))
                    .patchVersion(matchHistoryDto.getGameVersion())
                    .build()
            );
        }
    }

    /**
     * 플레이어 플레이 정보 + 픽 히스토리 저장
     * @param game 게임 정보
     * @param matchHistoryDto 게임 결과
     */
    private void savePlayerGameSummaryAndPickHistory(Game game, MatchHistoryDto matchHistoryDto){
        String gameVersion = matchHistoryDto.getGameVersion();

        int i = 0;
        for(ParticipantDto participantDto : matchHistoryDto.getParticipants()){
            // 플레이어 정보
            Player player = this.playerCommonService.findPlayerBySummonerName(
                    matchHistoryDto.findSummonerNameById(participantDto.getParticipantId()));
            // 플레이 정보
            PlayerGameSummary playerGameSummary = this.savePlayerGameSummary(game, player, i, participantDto);
            this.savePickHistory(playerGameSummary, gameVersion);
            i += 1;
        }
    }

    /**
     * 플레이어 게임 정보
     * @param game 게임
     * @param player 플레이어
     * @param seqNo 참여 차례
     * @param participantDto 게임결과 참여 정보
     * @return 플레이어 게임 정보
     */
    private PlayerGameSummary savePlayerGameSummary(Game game, Player player, int seqNo, ParticipantDto participantDto){
        // 픽한 챔피언 정보
        Champion champion = this.championCommonService.findById(participantDto.getChampionId().toString());
        // 사이드 정보
        String side = seqNo < 5 ? CommonCode.BLUE_SIDE.getCode() : CommonCode.RED_SIDE.getCode();
        // 포지션 역할
        LolRole lolRole = LolRole.findBySequence(seqNo);

        PlayerGameSummaryId playerGameSummaryId = PlayerGameSummaryId.builder()
                .gameId(game.getId())
                .playerId(player.getId())
                .build();

        PlayerGameSummary playerGameSummary = participantDto.getStats().toPlayerGameSummaryEntity();
        playerGameSummary.setPlayerGameSummaryId(playerGameSummaryId);
        playerGameSummary.setGame(game);
        playerGameSummary.setPlayer(player);
        playerGameSummary.setChampion(champion);
        playerGameSummary.setSide(side);
        playerGameSummary.setRole(lolRole);
        return this.playerGameSummaryRepository.save(playerGameSummary);
    }

    /**
     * 픽 정보 저장
     * @param playerGameSummary 플레이어 게임 결과
     */
    private void savePickHistory(PlayerGameSummary playerGameSummary, String gameVersion){
        PickHistoryId pickHistoryId = PickHistoryId.builder()
                .gameId(playerGameSummary.getGame().getId())
                .role(playerGameSummary.getRole())
                .side(playerGameSummary.getSide())
                .build();

        PickHistory pickHistory = PickHistory.builder()
                .pickHistoryId(pickHistoryId)
                .game(playerGameSummary.getGame())
                .player(playerGameSummary.getPlayer())
                .champion(playerGameSummary.getChampion())
                .patchVersion(gameVersion)
                .build();
        this.pickHistoryRepository.save(pickHistory);
    }
}
