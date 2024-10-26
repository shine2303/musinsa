package com.musinsa.admin.service;

import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.domain.BrandEntity;
import com.musinsa.admin.dto.BrandDto;
import com.musinsa.admin.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService{

    private final BrandRepository brandRepository;

    public BrandDto insertBrand(BrandDto brandDto) {
        //중복 브랜드 처리
        if (brandRepository.existsByName(brandDto.getName())) {
            throw new BusinessException(ErrorCode.BRAND_ALREADY_EXISTS, "이미 존재하는 브랜드명입니다.");
        }

        BrandEntity savedBrand = brandRepository.save(brandDto.toEntity());

        return BrandDto.builder().name(savedBrand.getName()).build();

    }
}
