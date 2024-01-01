package com.project.eventlink.item.repository;

import com.project.eventlink.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QItemRepository {
}
