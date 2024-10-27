package com.musinsa.admin.service;

import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.domain.BrandEntity;
import com.musinsa.admin.domain.CategoryEntity;
import com.musinsa.admin.dto.BrandDto;
import com.musinsa.admin.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService{

    private final BrandRepository brandRepository;

    public BrandDto insertBrand(BrandDto brandDto) {
        BrandEntity savedBrand = brandRepository.save(brandDto.toEntity());
        return BrandDto.from(savedBrand);

    }

    public boolean existsByName(String name){
        return brandRepository.existsByName(name);
    }

    public Optional<BrandEntity> findByName(String name){
        return brandRepository.findByName(name);
    }
}
