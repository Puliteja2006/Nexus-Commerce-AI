package com.nexuscommerce.dto.payment;

import com.nexuscommerce.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentIntentResponse {

    private String clientSecret;
    private String transactionId;
    private PaymentStatus status;
    private BigDecimal amount;
    private String publishableKey;
}
