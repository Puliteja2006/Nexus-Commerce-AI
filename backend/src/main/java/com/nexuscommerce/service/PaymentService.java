package com.nexuscommerce.service;

import com.nexuscommerce.dto.payment.PaymentIntentRequest;
import com.nexuscommerce.dto.payment.PaymentIntentResponse;
import com.nexuscommerce.dto.payment.PaymentTransactionDto;
import com.nexuscommerce.dto.payment.PaymentWebhookRequest;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentIntentResponse createPaymentIntent(String userEmail, PaymentIntentRequest request);

    PaymentTransactionDto processPaymentWebhook(PaymentWebhookRequest request);

    List<PaymentTransactionDto> getUserTransactions(String userEmail);

    PaymentTransactionDto refundTransaction(String adminEmail, String transactionId);
}
