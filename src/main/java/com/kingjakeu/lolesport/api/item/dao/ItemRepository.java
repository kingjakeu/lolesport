package com.kingjakeu.lolesport.api.item.dao;

import com.kingjakeu.lolesport.api.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
