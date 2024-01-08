package com.project.eventlink.item.option.repository;

import com.project.eventlink.item.option.domain.OptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionDetailRepository extends JpaRepository<OptionDetail, Long>, QOptionDetailRepository {
    List<OptionDetail> findOptionDetailByOptionOptionId(Long optionId);
}
