package com.musinsa.backend.controller;

import com.musinsa.backend.common.ApiResponse;
import com.musinsa.backend.dto.Report1Response;
import com.musinsa.backend.dto.Report2Response;
import com.musinsa.backend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/report")
@RestController
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;
    /*
        네이밍 : 가독성을 위해 단순하게 네이밍하였습니다.
        실무에서는 사용하지 않는 네이밍이지만, 과제 전형인 점을 고려하여 결정하였습니다.
     */
    @GetMapping("/v1")
    @Tag(name = "report", description = "1번 리포트")
    @Operation(description = "카테고리 별 최저 가격 브랜드 조회")
    public ApiResponse<Report1Response> execute1() {
        return ApiResponse.success(reportService.report1());
    }

    @GetMapping("/v2")
    @Tag(name = "report", description = "2번 리포트")
    @Operation(description = "단일 브랜드 풀세트 최저 가격")
    public ApiResponse<Report2Response> execute2() {
        return ApiResponse.success(reportService.report2());
    }

}
