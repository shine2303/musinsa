package com.musinsa.admin.repository;

import com.musinsa.admin.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

    Optional<CategoryEntity> findByName(String name);
}
