package com.project.eventlink.service;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.item.model.CreateItemRequestModel;
import com.project.eventlink.item.service.ItemService;
import com.project.eventlink.review.domain.Review;
import com.project.eventlink.review.model.CreateReviewRequestModel;
import com.project.eventlink.review.model.DeleteReviewRequestModel;
import com.project.eventlink.review.model.UpdateReviewRequestModel;
import com.project.eventlink.review.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class ReviewServiceTest extends BaseSpringBootTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ItemService itemService;


    @Test
    void addItemReview() {
        //Given
        String memberId = "test";
        Long itemId = itemService.addItem(new CreateItemRequestModel("name", 1000, 10, "detail", null));

        //When
        Long reviewId = reviewService.addReview(new CreateReviewRequestModel(memberId, itemId, null, "comment", 5));
        List<Review> reviews = reviewService.itemReviewList(itemId);

        //Then
        reviews.forEach(review -> assertThat(review.getReviewId()).isEqualTo(reviewId));
    }

    @Test
    void deleteReview() {
        //Given
        String memberId = "test";
        Long itemId = itemService.addItem(new CreateItemRequestModel("name", 1000, 10, "detail", null));
        Long reviewId = reviewService.addReview(new CreateReviewRequestModel(memberId, itemId, null, "comment", 5));

        //When
        reviewService.deleteReview(new DeleteReviewRequestModel(reviewId));

        //Then
        assertThat(reviewService.itemReviewList(itemId).size()).isEqualTo(0);
    }

    @Test
    void updateReview() {
        //Given
        String memberId = "test";
        Long itemId = itemService.addItem(new CreateItemRequestModel("name", 1000, 10, "detail", null));
        Long reviewId = reviewService.addReview(new CreateReviewRequestModel(memberId, itemId, null, "comment", 5));

        //When
        reviewService.updateReview(new UpdateReviewRequestModel(reviewId, "update comment", 3));
        Review review = reviewService.itemReviewList(itemId).get(0);

        //Then
        assertThat(review.getComment()).isEqualTo("update comment");
    }

    @Test
    void itemReviewList() {
        //Given
        String memberId = "test";
        Long itemId = itemService.addItem(new CreateItemRequestModel("name", 1000, 10, "detail", null));
        Long reviewId1 = reviewService.addReview(new CreateReviewRequestModel(memberId, itemId, null, "comment1", 5));
        Long reviewId2 = reviewService.addReview(new CreateReviewRequestModel(memberId, itemId, null, "comment2", 4));
        Long reviewId3 = reviewService.addReview(new CreateReviewRequestModel(memberId, itemId, null, "comment3", 3));

        //When
        List<Review> reviews = reviewService.itemReviewList(itemId);

        //Then
        assertThat(reviews.size()).isEqualTo(3);

    }
}