package com.nexuscommerce.dto.ai;

import com.nexuscommerce.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiChatResponse {

    private String reply;
    private String intentDetected;
    private List<ProductDto> recommendedProducts;
}
