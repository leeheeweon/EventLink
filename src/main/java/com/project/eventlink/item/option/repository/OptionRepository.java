package com.project.eventlink.item.option.repository;

import com.project.eventlink.item.option.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Long>, QOptionRepository {
    List<Option> findByItemItemId(Long itemId);
}
