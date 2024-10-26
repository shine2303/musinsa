package com.musinsa.admin.repository;

import com.musinsa.admin.domain.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, String> {


    BrandEntity save(BrandEntity brandEntity);
    boolean existsByName(String name);
}
