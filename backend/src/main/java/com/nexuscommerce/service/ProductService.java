package com.nexuscommerce.service;

import com.nexuscommerce.dto.product.CreateProductRequest;
import com.nexuscommerce.dto.product.ProductDto;
import com.nexuscommerce.dto.product.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Page<ProductDto> getProducts(String query, String categorySlug, Double minPrice, Double maxPrice, Pageable pageable);

    Page<ProductDto> getDealsProducts(Pageable pageable);

    Page<ProductDto> getNewArrivalsProducts(Pageable pageable);

    Page<ProductDto> getTrendingProducts(Pageable pageable);

    List<ProductDto> getFeaturedProducts();

    List<ProductDto> getProductsByCategorySlug(String categorySlug);

    ProductDto getProductBySlug(String slug);

    ProductDto getProductById(UUID id);

    ProductDto createProduct(String userEmail, CreateProductRequest request);

    ProductDto updateProduct(String userEmail, UUID id, UpdateProductRequest request);

    void deleteProduct(String userEmail, UUID id);

    List<ProductDto> getProductsBySellerEmail(String userEmail);
}
