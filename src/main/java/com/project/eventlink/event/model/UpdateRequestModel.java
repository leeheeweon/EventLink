package com.project.eventlink.event.model;

public record UpdateRequestModel(
        Long eventId,
        String name,
        Integer minPrice
) {
}
