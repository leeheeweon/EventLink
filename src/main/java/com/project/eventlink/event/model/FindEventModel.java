package com.project.eventlink.event.model;

public record FindEventModel(
        String name,
        Integer minPrice,
        String memberId
) {
}
