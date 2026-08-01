package com.nexuscommerce.service;

import com.nexuscommerce.dto.order.OrderDto;
import com.nexuscommerce.entity.Order;
import com.nexuscommerce.entity.OrderStatus;
import com.nexuscommerce.entity.PaymentMethod;
import com.nexuscommerce.entity.PaymentStatus;
import com.nexuscommerce.repository.OrderRepository;
import com.nexuscommerce.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrder = Order.builder()
                .id(UUID.randomUUID())
                .orderNumber("ORD-20260727-9911")
                .status(OrderStatus.PROCESSING)
                .paymentStatus(PaymentStatus.COMPLETED)
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .totalAmount(new BigDecimal("129.99"))
                .build();
    }

    @Test
    @DisplayName("Should successfully retrieve order by order number")
    void shouldGetOrderByOrderNumberSuccessfully() {
        when(orderRepository.findByOrderNumber("ORD-20260727-9911")).thenReturn(Optional.of(testOrder));

        OrderDto dto = orderService.getOrderByNumber("ORD-20260727-9911");

        assertNotNull(dto);
        assertEquals("ORD-20260727-9911", dto.getOrderNumber());
        assertEquals("PROCESSING", dto.getStatus());
        assertEquals("129.99", dto.getTotalAmount().toString());
    }
}
