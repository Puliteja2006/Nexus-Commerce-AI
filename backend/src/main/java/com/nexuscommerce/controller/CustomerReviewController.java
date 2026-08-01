package com.nexuscommerce.controller;

import com.nexuscommerce.common.response.ApiResponse;
import com.nexuscommerce.dto.review.CreateReviewRequest;
import com.nexuscommerce.dto.review.ReviewDto;
import com.nexuscommerce.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer/reviews")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CustomerReviewController {

    private final ReviewService reviewService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<ReviewDto>> createReview(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable UUID productId,
            @Valid @RequestBody CreateReviewRequest request
    ) {
        ReviewDto created = reviewService.createReview(userDetails.getUsername(), productId, request);
        return new ResponseEntity<>(ApiResponse.success("Review submitted successfully", created, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable UUID reviewId
    ) {
        reviewService.deleteReview(userDetails.getUsername(), reviewId);
        return ResponseEntity.ok(ApiResponse.success("Review deleted successfully", null));
    }
}
