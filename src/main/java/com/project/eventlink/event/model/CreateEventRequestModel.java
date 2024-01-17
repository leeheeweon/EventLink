package com.project.eventlink.event.model;

public record CreateEventRequestModel(
        String name,
        Integer minPrice,
        String memberId
) {
}
