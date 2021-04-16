package com.kingjakeu.promode.api.match.service;

import com.kingjakeu.promode.api.match.dto.response.MatchResultResDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    void getMatch() {
        List<MatchResultResDto> matchResultResDtoList = this.matchService.getMatch("20210318");
        for(MatchResultResDto resultResDto : matchResultResDtoList){
            System.out.println(resultResDto.toString());
        }
    }
}