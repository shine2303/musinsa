package com.musinsa.admin.service;

import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.dto.BrandDto;
import com.musinsa.admin.dto.ManageRequest;
import com.musinsa.admin.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService{

    private final BrandRepository brandRepository;

    public BrandDto insertBrand(ManageRequest request) {

        BrandDto brandDto = request.convertData(BrandDto.class);

        //중복 브랜드 처리 : update 가 수행될 수 있음
        if (existsByName(brandDto.getName())) {
            throw new BusinessException(ErrorCode.BRAND_ALREADY_EXISTS, "이미 존재하는 브랜드명입니다.");
        }

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
