package com.nexuscommerce.service;

import com.nexuscommerce.dto.cart.CartDto;
import com.nexuscommerce.dto.wishlist.WishlistDto;

import java.util.UUID;

public interface WishlistService {

    WishlistDto getUserWishlist(String userEmail);

    WishlistDto addItemToWishlist(String userEmail, UUID productId);

    WishlistDto removeItemFromWishlist(String userEmail, UUID productId);

    CartDto moveToCart(String userEmail, UUID productId, String sessionId);

    WishlistDto clearWishlist(String userEmail);
}
