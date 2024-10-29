package com.musinsa.admin.controller;

import com.musinsa.admin.common.ApiResponse;
import com.musinsa.admin.dto.ManageRequest;
import com.musinsa.admin.router.ServiceRouter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/manage")
@RestController
@AllArgsConstructor
public class ManageController {

    private final ServiceRouter serviceRouter;


    /**
     * 요구사항 분석
     *
     * 1. 운영자는 새로운 브랜드를 등록하고, 모든 브랜드의 상품을 추가, 변경, 삭제할 수 있어야 합니다.
     * 2. 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
     *
     * 요청값 - Request Body JSON
     * 응답값 - 성공 혹은 실패 여부 JSON, API 실패 시, 실패값과 실패 사유를 전달
    **/


    @PostMapping("/execute")
    @Tag(name = "Manage", description = "브랜드, 상품 관리")
    @Operation(description = "브랜드 추가, 상품 추가/수정/삭제를 수행")
    public ApiResponse<Object> execute(@RequestBody @Valid ManageRequest request) {

        Object result = serviceRouter.execute(request);
        return ApiResponse.success(result);
    }
}