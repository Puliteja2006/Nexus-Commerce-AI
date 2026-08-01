package com.nexuscommerce.service;

import com.nexuscommerce.dto.analytics.DashboardAnalyticsDto;

public interface AnalyticsService {

    DashboardAnalyticsDto getAdminDashboardAnalytics();

    DashboardAnalyticsDto getSellerDashboardAnalytics(String sellerEmail);
}
