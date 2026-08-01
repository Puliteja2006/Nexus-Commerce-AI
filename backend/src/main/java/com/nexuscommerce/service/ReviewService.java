package com.nexuscommerce.service;

import com.nexuscommerce.dto.review.CreateReviewRequest;
import com.nexuscommerce.dto.review.ProductReviewSummaryDto;
import com.nexuscommerce.dto.review.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<ReviewDto> getProductReviews(UUID productId);

    ProductReviewSummaryDto getProductReviewSummary(UUID productId);

    ReviewDto createReview(String userEmail, UUID productId, CreateReviewRequest request);

    void deleteReview(String userEmail, UUID reviewId);

    ReviewDto voteHelpful(UUID reviewId);
}
