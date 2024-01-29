package com.project.eventlink.event.model;

public record UpdateEventRequestModel(
        Long eventId,
        String name,
        Integer minPrice
) {
}
