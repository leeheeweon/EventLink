package com.project.eventlink.order.repository;

import com.project.eventlink.order.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
