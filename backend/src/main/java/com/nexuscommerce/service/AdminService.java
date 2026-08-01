package com.nexuscommerce.service;

import com.nexuscommerce.dto.admin.*;
import com.nexuscommerce.dto.seller.SellerStoreDto;
import com.nexuscommerce.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    AdminDashboardOverviewDto getAdminDashboardOverview();

    Page<AdminUserSummaryDto> getAllUsers(Pageable pageable);

    AdminUserSummaryDto updateUserRole(String adminEmail, UUID userId, Role newRole);

    AdminUserSummaryDto toggleUserStatus(String adminEmail, UUID userId, boolean enabled);

    List<SellerStoreDto> getAllSellerStores();

    Page<AuditLogDto> getAuditLogs(Pageable pageable);

    void recordAuditLog(UUID userId, String userEmail, String action, String resource, String details);
}
