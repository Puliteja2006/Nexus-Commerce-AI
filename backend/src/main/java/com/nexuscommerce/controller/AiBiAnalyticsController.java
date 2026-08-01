package com.nexuscommerce.controller;

import com.nexuscommerce.common.response.ApiResponse;
import com.nexuscommerce.dto.analytics.EnterpriseBiDashboardDto;
import com.nexuscommerce.service.AiBiAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/bi-analytics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AiBiAnalyticsController {

    private final AiBiAnalyticsService aiBiAnalyticsService;

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<EnterpriseBiDashboardDto>> getBiDashboard() {
        EnterpriseBiDashboardDto dashboard = aiBiAnalyticsService.getEnterpriseBiDashboard();
        return ResponseEntity.ok(ApiResponse.success("Enterprise BI Analytics Dashboard retrieved", dashboard));
    }

    @GetMapping(value = "/export/csv", produces = "text/csv")
    public ResponseEntity<String> exportCsvReport() {
        String csvContent = aiBiAnalyticsService.generateCsvReport();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"nexuscommerce-bi-report.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvContent);
    }
}
