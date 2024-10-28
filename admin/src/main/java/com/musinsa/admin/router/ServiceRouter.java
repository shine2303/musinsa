package com.musinsa.admin.router;

import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.dto.*;
import com.musinsa.admin.service.BrandService;
import com.musinsa.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 서비스 라우터 레이어
 *
 * 적절한 서비스레이어를 호출하기위해 선별하는 레이어
 * controller 와 service 레이어 사이에 위치한 레이어
 * 의도1 : 통합 관리 api로 여러 엔티티 및 액션들이 추가됨을 고려
 * 의도2 : 해당 레이어를 추가함으로써 controller 와 service 레이어를 흔히 아는 구조&단순한 구조로 가져가려는 의도
 */
@Component
@RequiredArgsConstructor
public class ServiceRouter {

    private final BrandService brandService;
    private final ProductService productService;

    public Object execute(ManageRequest request) {

        // 확장성 고려 : 관리해야할 대상이 3개 이상이면 개별 클래스(상품,브랜드,카테고리,xxx)형태로 구성했을 것
        return switch (request.getEntityType()) {
            case BRAND -> handleBrand(request);
            case PRODUCT -> handleProduct(request);
        };
    }

    private Object handleBrand(ManageRequest request) {
        //insert 만 수행.(해당 레이어에서 액션에 대한 예외처리)
        if (request.getAction() != ManageRequest.Action.INSERT) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "브랜드는 추가만 가능합니다.");
        }

        return brandService.insertBrand(request);
    }

    private Object handleProduct(ManageRequest request) {

        //action 수행(crud)
        return switch (request.getAction()) {
            case INSERT -> productService.insertProduct(request);
            case UPDATE -> productService.updateProduct(request);
            case DELETE -> productService.deleteProduct(request);
        };
    }
}
