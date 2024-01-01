package com.project.eventlink.item.model;

public record UpdateItemRequestModel(Long id,
                                     String name,
                                     int price,
                                     int quantity,
                                     String detail,
                                     String sellStatus) {
}
