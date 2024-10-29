package com.musinsa.admin.service;

import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.entity.CategoryEntity;
import com.musinsa.admin.entity.ProductEntity;
import com.musinsa.admin.dto.ManageRequest;
import com.musinsa.admin.dto.ProductDto;
import com.musinsa.admin.repository.BrandRepository;
import com.musinsa.admin.repository.CategoryRepository;
import com.musinsa.admin.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductDto insertProduct(ManageRequest request) {

        // 0. 상품 파라미터 검증
        ProductDto productDto = request.convertData(ProductDto.class);
        validateProductParams(productDto);

        // 1. Category 존재 여부 확인
        CategoryEntity categoryEntity = categoryRepository.findByName(productDto.getCategory().getName())
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND, "존재하지 않는 카테고리입니다."));

        // 2. Brand 존재 여부 확인
        BrandEntity brandEntity = brandRepository.findByName(productDto.getBrand().getName())
                .orElseThrow(() -> new BusinessException(ErrorCode.BRAND_NOT_FOUND, "존재하지 않는 브랜드입니다."));

        // 3. 상품 중복 체크
        boolean isExistProduct = productRepository.existsByNameAndPriceAndCategoryAndBrand(
                productDto.getName(), productDto.getPrice(), categoryEntity, brandEntity);
        if (isExistProduct){
            throw new BusinessException(ErrorCode.PRODUCT_ALREADY_EXISTS, "이미 존재하는 상품입니다.");
        }

        // 4. 상품 save
        ProductEntity savedProduct = productRepository.save(productDto.toEntity(categoryEntity, brandEntity));
        return ProductDto.from(savedProduct);
    }

    public ProductDto updateProduct(ManageRequest request){

        // 0. 상품 파라미터 검증
        ProductDto productDto = request.convertData(ProductDto.class);
        validateProductParams(productDto);

        // 1. 상품 존재 확인(jpa save 시 upsert)
        productRepository.findById(productDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "존재하지 않는 상품입니다."));

        // 2. Category 존재 여부 확인
        CategoryEntity categoryEntity = categoryRepository.findByName(productDto.getCategory().getName())
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND, "존재하지 않는 카테고리입니다."));

        // 3. Brand 존재 여부 확인
        BrandEntity brandEntity = brandRepository.findByName(productDto.getBrand().getName())
                .orElseThrow(() -> new BusinessException(ErrorCode.BRAND_NOT_FOUND, "존재하지 않는 브랜드입니다."));

        // 4. 상품 save
        ProductEntity savedProduct = productRepository.save(productDto.toEntity(categoryEntity, brandEntity));
        return ProductDto.from(savedProduct);
    }

    public ProductDto deleteProduct(ManageRequest request){

        ProductDto productDto = request.convertData(ProductDto.class);

        // 1. 상품 존재 확인
        ProductEntity productEntity = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "존재하지 않는 상품입니다."));

        // 2. 상품 soft 삭제
        productRepository.deleteById(productDto.getId());
        return ProductDto.from(productEntity);
    }

    private void validateProductParams(ProductDto productDto){
        String productName = productDto.getName();
        long productPrice = productDto.getPrice();

        if(StringUtils.isBlank(productName)){
            throw new BusinessException(ErrorCode.PRODUCT_NAME_INVALID);
        }

        if(productPrice <= 0){
            throw new BusinessException(ErrorCode.PRODUCT_PRICE_INVALID);
        }

        if(null == productDto.getBrand() || StringUtils.isBlank(productDto.getBrand().getName())){
            throw new BusinessException(ErrorCode.BRAND_NOT_FOUND);
        }

        if (null == productDto.getCategory() || StringUtils.isBlank(productDto.getCategory().getName())){
            throw new BusinessException(ErrorCode.CATEGORY_NOT_FOUND);
        }
    }
}
