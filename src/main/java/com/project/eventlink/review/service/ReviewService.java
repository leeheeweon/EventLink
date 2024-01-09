package com.project.eventlink.review.service;

import com.project.eventlink.review.domain.Review;
import com.project.eventlink.review.model.CreateReviewRequestModel;
import com.project.eventlink.review.model.DeleteReviewRequestModel;
import com.project.eventlink.review.model.UpdateReviewRequestModel;

import java.util.List;

public interface ReviewService {

    Long addReview(CreateReviewRequestModel createReviewRequestModel);

    void deleteReview(DeleteReviewRequestModel deleteReviewRequestModel);

    void updateReview(UpdateReviewRequestModel updateReviewRequestModel);

    List<Review> eventReviewList(Long eventId);
    List<Review> itemReviewList(Long itemId);
}
