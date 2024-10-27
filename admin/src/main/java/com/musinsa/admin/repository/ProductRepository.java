package com.musinsa.admin.repository;

import com.musinsa.admin.domain.BrandEntity;
import com.musinsa.admin.domain.CategoryEntity;
import com.musinsa.admin.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

    @NonNull
    <S extends ProductEntity> S save(@NonNull S ProductEntity);

    boolean existsByNameAndPriceAndCategoryAndBrand(String name, Long price, CategoryEntity category, BrandEntity brand);
    Optional<ProductEntity> findById(Long id);

}
