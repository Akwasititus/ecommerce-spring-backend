package com.titi.ecommercebackend.repository;


import com.titi.ecommercebackend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

@Query("""
SELECT p FROM Product p WHERE
(:categoryId IS NULL OR p.category.id = :categoryId)
AND (:minPrice IS NULL OR p.price >= :minPrice)
AND (:maxPrice IS NULL OR p.price <= :maxPrice)
AND (
    :search IS NULL OR\s
    LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))
)
""")
    Page<Product> findWithFilters(@Param("categoryId") Long categoryId,
                                  @Param("minPrice") BigDecimal minPrice,
                                  @Param("maxPrice") BigDecimal maxPrice,
                                  @Param("search") String search,
                                  Pageable pageable);
}


