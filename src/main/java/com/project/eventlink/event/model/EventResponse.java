package com.project.eventlink.event.model;

import com.project.eventlink.entity.Member;

public record EventResponse(
        String name,
        Integer minPrice,
        Member member
) {}
