package com.nexuscommerce.service;

import com.nexuscommerce.dto.ai.*;
import com.nexuscommerce.dto.product.ProductDto;

import java.util.List;

public interface AiVisionSearchService {

    List<ProductDto> searchByImage(ImageSearchRequest request);

    ProductComparisonResponse compareProducts(ProductComparisonRequest request);

    List<String> getTrendingSearchKeywords();

    SearchAnalyticsDto getSearchAnalytics();
}
