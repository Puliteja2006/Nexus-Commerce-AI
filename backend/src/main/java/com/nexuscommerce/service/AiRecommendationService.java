package com.nexuscommerce.service;

import com.nexuscommerce.dto.ai.AiChatRequest;
import com.nexuscommerce.dto.ai.AiChatResponse;
import com.nexuscommerce.dto.ai.RecommendationResponse;
import com.nexuscommerce.dto.product.ProductDto;

import java.util.List;
import java.util.UUID;

public interface AiRecommendationService {

    RecommendationResponse getPersonalizedRecommendations(String userEmail);

    List<ProductDto> getSimilarProducts(UUID productId);

    AiChatResponse chatWithAiAssistant(String userEmail, AiChatRequest request);
}
