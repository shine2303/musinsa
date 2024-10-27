package com.musinsa.admin.repository;

import com.musinsa.admin.domain.CategoryEntity;
import com.musinsa.admin.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

    Optional<CategoryEntity> findByName(String name);
}
