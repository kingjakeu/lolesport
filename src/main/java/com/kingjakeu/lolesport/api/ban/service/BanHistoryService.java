package com.kingjakeu.lolesport.api.ban.service;

import com.kingjakeu.lolesport.api.ban.dao.BanHistoryRepository;
import com.kingjakeu.lolesport.api.ban.domain.BanHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BanHistoryService {

    private final BanHistoryRepository banHistoryRepository;

    public void mostBans(String patchVersion){
        List<BanHistory> banHistoryList = this.banHistoryRepository.findAllByPatchVersion(patchVersion);
        Map<String, Integer> banCounts = new LinkedHashMap<>();

        for(BanHistory banHistory : banHistoryList){
            String key = banHistory.getBannedChampion().getId();
            if(banCounts.containsKey(key)){
                banCounts.put(key, banCounts.get(key) +1);
            }else{
                banCounts.put(key, 1);
            }
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(banCounts.entrySet());
        Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue));
        System.out.println(banCounts);
    }
}
