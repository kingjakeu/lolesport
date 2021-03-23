package com.kingjakeu.lolesport.api.pick.service;

import com.kingjakeu.lolesport.api.pick.dao.PickHistoryRepository;
import com.kingjakeu.lolesport.api.pick.domain.PickHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PickHistoryCommonService {

    private final PickHistoryRepository pickHistoryRepository;
}
