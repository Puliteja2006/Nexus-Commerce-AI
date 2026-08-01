package com.nexuscommerce.dto.payment;

import com.nexuscommerce.entity.PaymentMethod;
import com.nexuscommerce.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransactionDto {

    private UUID id;
    private String orderNumber;
    private String transactionId;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private BigDecimal amount;
    private String gatewayResponse;
    private LocalDateTime createdAt;
}
