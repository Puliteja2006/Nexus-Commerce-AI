package com.nexuscommerce.service;

import com.nexuscommerce.dto.coupon.ApplyCouponRequest;
import com.nexuscommerce.dto.coupon.CouponDto;
import com.nexuscommerce.dto.coupon.CouponValidationResponse;
import com.nexuscommerce.dto.coupon.CreateCouponRequest;

import java.util.List;
import java.util.UUID;

public interface CouponService {

    CouponValidationResponse validateAndCalculateCoupon(ApplyCouponRequest request);

    List<CouponDto> getActivePublicCoupons();

    List<CouponDto> getAllCoupons();

    CouponDto createCoupon(String adminEmail, CreateCouponRequest request);

    CouponDto toggleCouponStatus(String adminEmail, UUID couponId, boolean active);

    void incrementCouponUsage(String code);
}
