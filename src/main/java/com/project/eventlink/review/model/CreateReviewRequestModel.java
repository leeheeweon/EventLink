package com.project.eventlink.review.model;

public record CreateReviewRequestModel(String memberId,
                                       Long itemId,
                                       Long eventId,
                                       String comment,
                                       Integer star) {
}

