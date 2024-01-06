package com.project.eventlink.item.option.repository;

import com.project.eventlink.item.option.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long>, QOptionRepository {
}
