package com.titi.ecommercebackend.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductFilter {
    private Long categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String search; // Search in name/description
}
