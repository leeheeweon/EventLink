package com.project.eventlink.review.service;

import com.project.eventlink.event.repository.EventJpaRepository;
import com.project.eventlink.item.repository.ItemRepository;
import com.project.eventlink.member.repository.MemberRepository;
import com.project.eventlink.review.domain.Review;
import com.project.eventlink.review.model.CreateReviewRequestModel;
import com.project.eventlink.review.model.DeleteReviewRequestModel;
import com.project.eventlink.review.model.UpdateReviewRequestModel;
import com.project.eventlink.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final EventJpaRepository eventRepository;

    @Override
    public Long addReview(CreateReviewRequestModel createReviewRequestModel) {
        Review review = Review.builder()
                .member(memberRepository.findByMemberId(createReviewRequestModel.memberId()))
                .item(itemRepository.findByItemId(createReviewRequestModel.itemId()))
                .event(eventRepository.findByEventId(createReviewRequestModel.eventId()))
                .comment(createReviewRequestModel.comment())
                .star(createReviewRequestModel.star())
                .build();

        return reviewRepository.save(review).getReviewId();
    }

    @Override
    public void deleteReview(DeleteReviewRequestModel deleteReviewRequestModel) {
        reviewRepository.deleteById(deleteReviewRequestModel.id());
    }

    @Override
    public void updateReview(UpdateReviewRequestModel updateReviewRequestModel) {
        Review review = reviewRepository.findByReviewId(updateReviewRequestModel.id());
        review.setComment(updateReviewRequestModel.comment());
        review.setStar(updateReviewRequestModel.star());
    }

    @Override
    public List<Review> eventReviewList(Long eventId) {
        return reviewRepository.findByEventEventId(eventId);
    }

    @Override
    public List<Review> itemReviewList(Long itemId) {
        return reviewRepository.findByItemItemId(itemId);
    }
}
