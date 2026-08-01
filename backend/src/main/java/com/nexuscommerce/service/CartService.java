package com.nexuscommerce.service;

import com.nexuscommerce.dto.cart.AddToCartRequest;
import com.nexuscommerce.dto.cart.CartDto;
import com.nexuscommerce.dto.cart.UpdateCartItemRequest;

import java.util.UUID;

public interface CartService {

    CartDto getCart(String userEmail, String sessionId);

    CartDto addItemToCart(String userEmail, String sessionId, AddToCartRequest request);

    CartDto updateItemQuantity(String userEmail, String sessionId, UUID cartItemId, UpdateCartItemRequest request);

    CartDto removeItemFromCart(String userEmail, String sessionId, UUID cartItemId);

    CartDto clearCart(String userEmail, String sessionId);

    void mergeGuestCartToUser(String userEmail, String sessionId);
}
