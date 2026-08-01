package com.nexuscommerce.dto.order;

import com.nexuscommerce.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {

    private UUID addressId;

    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingCity;
    private String shippingState;
    private String shippingPostalCode;
    private String shippingCountry;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    private String shippingOptionId; // STD_GROUND, PRIORITY_EXPRESS, OVERNIGHT_COURIER

    private String couponCode;
}
