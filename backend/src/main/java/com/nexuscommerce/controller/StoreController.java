package com.nexuscommerce.controller;

import com.nexuscommerce.common.response.ApiResponse;
import com.nexuscommerce.dto.seller.SellerStoreDto;
import com.nexuscommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final SellerService sellerService;

    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<SellerStoreDto>> getStoreBySlug(@PathVariable String slug) {
        SellerStoreDto store = sellerService.getSellerStoreBySlug(slug);
        return ResponseEntity.ok(ApiResponse.success("Store details fetched", store));
    }
}
