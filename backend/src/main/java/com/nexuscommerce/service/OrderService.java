package com.nexuscommerce.service;

import com.nexuscommerce.dto.order.OrderDto;
import com.nexuscommerce.dto.order.PlaceOrderRequest;
import com.nexuscommerce.dto.order.UpdateOrderStatusRequest;
import com.nexuscommerce.entity.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDto placeOrder(String userEmail, String sessionId, PlaceOrderRequest request);

    List<OrderDto> getUserOrders(String userEmail);

    OrderDto getOrderByNumber(String userEmail, String orderNumber);

    OrderDto cancelOrder(String userEmail, String orderNumber);

    List<OrderDto> getMerchantOrders(String sellerEmail);

    OrderDto updateOrderStatus(String userEmail, String orderNumber, UpdateOrderStatusRequest request);

    List<OrderDto> getAllOrdersForAdmin();
}
