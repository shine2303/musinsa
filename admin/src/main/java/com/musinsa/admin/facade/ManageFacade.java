package com.musinsa.admin.facade;

import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.dto.*;
import com.musinsa.admin.service.BrandService;
import com.musinsa.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ManageFacade {

    private final BrandService brandService;
    private final ProductService productService;

    public Object execute(ManageRequest request) {

        return switch (request.getEntityType()) {
            case PRODUCT -> handleProduct(request);
            case BRAND -> handleBrand(request);
        };
    }

    private BrandDto handleBrand(ManageRequest request) {

        if (request.getAction() != ManageRequest.Action.INSERT) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "브랜드는 추가만 가능합니다.");
        }

        BrandDto brandDto = request.convertData(BrandDto.class);
        return brandService.insertBrand(brandDto);
    }

    private Object handleProduct(ManageRequest request) {
        ProductDto productDto = request.convertData(ProductDto.class);

        return null;
//        return switch (request.getAction()) {
//            case INSERT -> productService.insertEntity(productDto);
//            case UPDATE -> productService.updateEntity(productDto);
//            case DELETE -> {
//                productService.deleteEntity(productDto);
//                yield null;
//            }
//        };
    }
}
