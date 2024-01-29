package com.project.eventlink.order.model;

public record CreateOrderModel(
        Long itemId,
        Integer count,
        String city,
        String street,
        String zipcode
) {

}
