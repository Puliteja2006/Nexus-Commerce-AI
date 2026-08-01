package com.nexuscommerce.service;

import com.nexuscommerce.dto.seller.*;

import java.util.UUID;

public interface SellerService {

    SellerStoreDto createSellerStore(String userEmail, CreateSellerStoreRequest request);

    SellerStoreDto getSellerStoreByEmail(String userEmail);

    SellerStoreDto getSellerStoreBySlug(String storeSlug);

    SellerStoreDto updateSellerStore(String userEmail, UpdateSellerStoreRequest request);

    SellerDashboardOverviewDto getSellerDashboardOverview(String userEmail);

    SellerStoreDto verifySellerStore(UUID storeId, boolean verify);
}
