package com.kingjakeu.promode.api.pick.service;

import com.kingjakeu.promode.api.pick.dao.PickHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PickHistoryCommonService {

    private final PickHistoryRepository pickHistoryRepository;
}
