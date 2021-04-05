package com.kingjakeu.promode.api.item.dao;

import com.kingjakeu.promode.api.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
