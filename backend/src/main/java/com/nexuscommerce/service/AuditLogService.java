package com.nexuscommerce.service;

import com.nexuscommerce.dto.audit.AuditLogDto;
import com.nexuscommerce.dto.audit.AuditLogSearchRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AuditLogService {

    void logActivity(String userEmail, String action, String resource, String details, String ipAddress);

    Page<AuditLogDto> getAuditLogs(int page, int size);

    Page<AuditLogDto> searchAuditLogs(AuditLogSearchRequest request);
}
