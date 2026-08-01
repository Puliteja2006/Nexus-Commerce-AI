package com.nexuscommerce.dto.coupon;

import com.nexuscommerce.entity.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponValidationResponse {

    private boolean valid;
    private String code;
    private DiscountType discountType;
    private BigDecimal discountValue;
    private BigDecimal calculatedDiscount;
    private String message;
}
