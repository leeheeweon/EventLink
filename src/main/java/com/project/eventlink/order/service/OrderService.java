package com.project.eventlink.order.service;

import com.project.eventlink.order.model.CreateOrderModel;

public interface OrderService {
    Long makeOrder(CreateOrderModel createOrderModel);
}
