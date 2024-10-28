package com.musinsa.admin.repository;

import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.entity.CategoryEntity;
import com.musinsa.admin.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
