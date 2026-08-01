package com.nexuscommerce.service;

import com.nexuscommerce.dto.analytics.EnterpriseBiDashboardDto;

public interface AiBiAnalyticsService {

    EnterpriseBiDashboardDto getEnterpriseBiDashboard();

    String generateCsvReport();
}
