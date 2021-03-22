package com.kingjakeu.lolesport.api.ban.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class BanHistoryServiceTest {

    @Autowired
    BanHistoryService banHistoryService;

    @Test
    void mostBans() {
        banHistoryService.mostBans("11.5.365.4968");
    }
}