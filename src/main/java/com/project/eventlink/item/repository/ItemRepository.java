package com.project.eventlink.item.repository;

import com.project.eventlink.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemId(Long itemId);
}
