package com.project.eventlink.order.model;

public record CreateOrderModel(
        Long itemId,
        Integer count
) {
}
