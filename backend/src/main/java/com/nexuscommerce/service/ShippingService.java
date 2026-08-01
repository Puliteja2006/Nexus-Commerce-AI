package com.nexuscommerce.service;

import com.nexuscommerce.dto.shipping.ShippingOptionDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ShippingService {

    List<ShippingOptionDto> getAvailableShippingMethods(String userEmail, UUID addressId, BigDecimal subtotal);
}
