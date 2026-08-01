package com.nexuscommerce.controller;

import com.nexuscommerce.common.response.ApiResponse;
import com.nexuscommerce.dto.seller.SellerStoreDto;
import com.nexuscommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/sellers")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminSellerController {

    private final SellerService sellerService;

    @PutMapping("/{storeId}/verify")
    public ResponseEntity<ApiResponse<SellerStoreDto>> verifyStore(
            @PathVariable UUID storeId,
            @RequestParam(defaultValue = "true") boolean verify
    ) {
        SellerStoreDto store = sellerService.verifySellerStore(storeId, verify);
        String msg = verify ? "Seller store verified successfully" : "Seller store verification revoked";
        return ResponseEntity.ok(ApiResponse.success(msg, store));
    }
}
