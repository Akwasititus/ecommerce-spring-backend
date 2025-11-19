package com.titi.ecommercebackend.service.userserviceinterface;


import com.titi.ecommercebackend.dto.CategoryRequest;
import com.titi.ecommercebackend.dto.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
}
