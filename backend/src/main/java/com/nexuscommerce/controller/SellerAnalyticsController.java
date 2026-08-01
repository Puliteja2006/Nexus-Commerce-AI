package com.nexuscommerce.controller;

import com.nexuscommerce.common.response.ApiResponse;
import com.nexuscommerce.dto.analytics.DashboardAnalyticsDto;
import com.nexuscommerce.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
public class SellerAnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardAnalyticsDto>> getSellerAnalytics(@AuthenticationPrincipal UserDetails userDetails) {
        DashboardAnalyticsDto analytics = analyticsService.getSellerDashboardAnalytics(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Seller store analytics retrieved", analytics));
    }
}
