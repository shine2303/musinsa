package com.musinsa.admin.repository;

import com.musinsa.admin.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    boolean existsByName(String name);

    Optional<BrandEntity> findByName(String name);

}
