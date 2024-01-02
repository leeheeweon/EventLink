package com.project.eventlink.item.model;

public record CreateItemRequestModel(
        String name,
        int price,
        int quantity,
        String detail
) {}
