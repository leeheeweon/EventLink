package com.project.eventlink.order.repository;

import com.project.eventlink.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
