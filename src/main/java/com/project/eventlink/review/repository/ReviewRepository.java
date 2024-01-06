package com.project.eventlink.review.repository;

import com.project.eventlink.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByEventEventId(Long eventId);
    List<Review> findByItemItemId(Long itemId);

    Review findByReviewId(Long reviewId);
}
