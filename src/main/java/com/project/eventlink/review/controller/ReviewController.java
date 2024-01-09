package com.project.eventlink.review.controller;

import com.project.eventlink.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {
    private final ReviewService reviewService;


}
