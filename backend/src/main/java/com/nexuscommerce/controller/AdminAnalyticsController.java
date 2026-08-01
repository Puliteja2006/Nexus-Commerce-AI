package com.nexuscommerce.controller;

import com.nexuscommerce.common.response.ApiResponse;
import com.nexuscommerce.dto.analytics.DashboardAnalyticsDto;
import com.nexuscommerce.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminAnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardAnalyticsDto>> getAdminAnalytics() {
        DashboardAnalyticsDto analytics = analyticsService.getAdminDashboardAnalytics();
        return ResponseEntity.ok(ApiResponse.success("Admin dashboard analytics retrieved", analytics));
    }
}
