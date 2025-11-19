package com.titi.ecommercebackend.service.userserviceinterface;


import com.titi.ecommercebackend.dto.ProductDto;
import com.titi.ecommercebackend.dto.ProductFilter;
import com.titi.ecommercebackend.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createProduct(ProductDto request);

    Page<ProductResponse> getProducts(ProductFilter filter, Pageable pageable);
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductDto request);
    void deleteProduct(Long id);
}
