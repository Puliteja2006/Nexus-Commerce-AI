package com.nexuscommerce.controller;

import com.nexuscommerce.common.response.ApiResponse;
import com.nexuscommerce.dto.ai.AiChatRequest;
import com.nexuscommerce.dto.ai.AiChatResponse;
import com.nexuscommerce.dto.ai.RecommendationResponse;
import com.nexuscommerce.dto.product.ProductDto;
import com.nexuscommerce.service.AiRecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiRecommendationController {

    private final AiRecommendationService aiRecommendationService;

    @GetMapping("/recommendations/personalized")
    public ResponseEntity<ApiResponse<RecommendationResponse>> getPersonalizedRecommendations(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails != null ? userDetails.getUsername() : null;
        RecommendationResponse response = aiRecommendationService.getPersonalizedRecommendations(email);
        return ResponseEntity.ok(ApiResponse.success("Personalized AI recommendations fetched", response));
    }

    @GetMapping("/recommendations/similar/{productId}")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getSimilarProducts(@PathVariable UUID productId) {
        List<ProductDto> similar = aiRecommendationService.getSimilarProducts(productId);
        return ResponseEntity.ok(ApiResponse.success("Similar products fetched", similar));
    }

    @PostMapping("/chat")
    public ResponseEntity<ApiResponse<AiChatResponse>> chatWithAiAssistant(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody AiChatRequest request
    ) {
        String email = userDetails != null ? userDetails.getUsername() : null;
        AiChatResponse response = aiRecommendationService.chatWithAiAssistant(email, request);
        return ResponseEntity.ok(ApiResponse.success("AI response generated", response));
    }
}
