package com.nexuscommerce.dto.ai;

import com.nexuscommerce.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiAssistantResponse {

    private String reply;
    private String intent;
    private List<ProductDto> recommendedProducts;
    private List<String> suggestedPrompts;
    private LocalDateTime timestamp;
}
