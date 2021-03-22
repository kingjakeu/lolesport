package com.kingjakeu.lolesport.api.match.service;

import com.kingjakeu.lolesport.api.match.dao.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchCommonService {
    private final MatchRepository matchRepository;

}
