package com.project.eventlink.order.model;

import com.project.eventlink.entity.Address;

public record CreateOrderModel(
        Long itemId,
        Integer count,
        String city,
        String street,
        String zipcode
) {

}
