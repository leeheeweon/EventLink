package com.project.eventlink.event.model;

public record FindEventListModel(
        String name,
        Integer minPrice,
        String memberId
) {
}
