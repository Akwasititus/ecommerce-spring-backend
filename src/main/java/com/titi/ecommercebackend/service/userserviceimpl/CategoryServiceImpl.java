package com.titi.ecommercebackend.service.userserviceimpl;


import com.titi.ecommercebackend.dto.CategoryRequest;
import com.titi.ecommercebackend.dto.CategoryResponse;
import com.titi.ecommercebackend.entity.Category;
import com.titi.ecommercebackend.repository.CategoryRepository;
import com.titi.ecommercebackend.service.userserviceinterface.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Category savedCategory = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .description(savedCategory.getDescription())
                .createdAt(savedCategory.getCreatedAt())
                .updatedAt(savedCategory.getUpdatedAt())
                .build();
    }
}
