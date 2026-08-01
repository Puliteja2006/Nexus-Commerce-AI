package com.nexuscommerce.dto.admin;

import com.nexuscommerce.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserSummaryDto {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private boolean enabled;
    private String storeName;
    private LocalDateTime createdAt;
}
