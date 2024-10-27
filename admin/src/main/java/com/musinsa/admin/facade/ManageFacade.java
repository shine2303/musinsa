package com.musinsa.admin.facade;

import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.domain.BrandEntity;
import com.musinsa.admin.domain.CategoryEntity;
import com.musinsa.admin.domain.ProductEntity;
import com.musinsa.admin.dto.*;
import com.musinsa.admin.service.BrandService;
import com.musinsa.admin.service.CategoryService;
import com.musinsa.admin.service.ProductService;
import io.micrometer.common.util.StringUtils;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 파사드 레이어
 *
 * 적절한 서비스레이어를 호출하기위해 선별하는 레이어
 * controller 와 service 레이어 사이에 위치한 레이어
 * 해당 레이어를 추가함으로써 controller 와 service 레이어를 흔히 아는 구조&단순한 구조로 가져가려는 의도
 */
@Component
@RequiredArgsConstructor
public class ManageFacade {

    private final BrandService brandService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public Object execute(ManageRequest request) {

        return switch (request.getEntityType()) {
            case BRAND -> handleBrand(request);
            case PRODUCT -> handleProduct(request);
        };
    }

    private BrandDto handleBrand(ManageRequest request) {
        //insert 만 수행
        if (request.getAction() != ManageRequest.Action.INSERT) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "브랜드는 추가만 가능합니다.");
        }

        BrandDto brandDto = request.convertData(BrandDto.class);

        //중복 브랜드 처리 : update 가 수행될 수 있음
        if (brandService.existsByName(brandDto.getName())) {
            throw new BusinessException(ErrorCode.BRAND_ALREADY_EXISTS, "이미 존재하는 브랜드명입니다.");
        }

        return brandService.insertBrand(brandDto);
    }

    private ProductDto handleProduct(ManageRequest request) {

        //1. 상품 파라미터 검증
        ProductDto productDto = request.convertData(ProductDto.class);

        //2. action 수행(crud)
        return switch (request.getAction()) {
            case INSERT -> handleInsertProduct(productDto);
            case UPDATE -> handleUpdateProduct(productDto);
            case DELETE -> handleDeleteProduct(productDto);
        };
    }

    private ProductDto handleInsertProduct(ProductDto productDto){

        // 0. 상품 파라미터 검증
        validateProductParams(productDto);

        // 1. Category 존재 여부 확인
        CategoryEntity categoryEntity = findCategoryByName(productDto.getCategory().getName());

        // 2. Brand 존재 여부 확인
        BrandEntity brandEntity = findBrandByName(productDto.getBrand().getName());

        // 3. 상품 중복 체크
        if (productService.existsByNameAndPriceAndCategoryAndBrand(productDto,categoryEntity,brandEntity)){
            throw new BusinessException(ErrorCode.PRODUCT_ALREADY_EXISTS, "이미 존재하는 상품입니다.");
        }

        // 5. 상품 save
        return productService.saveProduct(productDto, categoryEntity, brandEntity);
    }

    private ProductDto handleUpdateProduct(ProductDto productDto){

        // 0. 상품 파라미터 검증
        validateProductParams(productDto);

        // 1. 상품 존재 확인
        productService.findById(productDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "존재하지 않는 상품입니다."));

        // 2. Category 존재 여부 확인
        CategoryEntity categoryEntity = findCategoryByName(productDto.getCategory().getName());

        // 3. Brand 존재 여부 확인
        BrandEntity brandEntity = findBrandByName(productDto.getBrand().getName());


        // 4. 상품 save
        return productService.saveProduct(productDto, categoryEntity, brandEntity);
    }

    private CategoryEntity findCategoryByName(String name) {
        return categoryService.findByName(name)
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND, "존재하지 않는 카테고리입니다."));
    }

    private BrandEntity findBrandByName(String name) {
        return brandService.findByName(name)
                .orElseThrow(() -> new BusinessException(ErrorCode.BRAND_NOT_FOUND, "존재하지 않는 브랜드입니다."));
    }

    private ProductDto handleDeleteProduct(ProductDto productDto){
        // 1. 상품 존재 확인
        ProductEntity productEntity = productService.findById(productDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "존재하지 않는 상품입니다."));

        // 2. 상품 soft 삭제
        productService.deleteProduct(productDto.getId());
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
