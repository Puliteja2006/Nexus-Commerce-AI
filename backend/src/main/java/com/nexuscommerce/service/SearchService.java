package com.nexuscommerce.service;

import com.nexuscommerce.dto.product.ProductDto;
import com.nexuscommerce.dto.search.SearchFilterRequest;
import com.nexuscommerce.dto.search.SearchResultDto;

import java.util.List;

public interface SearchService {

    SearchResultDto searchProducts(SearchFilterRequest request);

    List<ProductDto> getSearchSuggestions(String query);
}
