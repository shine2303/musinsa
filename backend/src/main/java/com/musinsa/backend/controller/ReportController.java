package com.musinsa.backend.controller;

import com.musinsa.backend.common.ApiResponse;
import com.musinsa.backend.dto.Report1Response;
import com.musinsa.backend.dto.Report2Response;
import com.musinsa.backend.dto.Report3Response;
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
        실무에서는 사용하지 않는 네이밍&url 이지만, 과제 전형인 점을 고려하여 결정하였습니다.
        또한 오히려 조건이 변경되면 네이밍이 변경되는 번거로움이 있을 수 있어 통계성 조회 api가 많아진다면 아래와 같이 네이밍하는것도 하나의 방법 같습니다.
     */
    @GetMapping("/v1")
    @Tag(name = "report", description = "리포트")
    @Operation(description = "카테고리 별 최저 가격 브랜드 조회")
    public ApiResponse<Report1Response> execute1() {
        return ApiResponse.success(reportService.report1());
    }

    @GetMapping("/v2")
    @Tag(name = "report", description = "리포트")
    @Operation(description = "단일 브랜드 풀세트 최저 가격")
    public ApiResponse<Report2Response> execute2() {
        return ApiResponse.success(reportService.report2());
    }

    @GetMapping("/v3")
    @Tag(name = "report", description = "리포트")
    @Operation(description = "지정 카테고리의 최저/최고 브랜드")
    public ApiResponse<Report3Response> execute3(String categoryName) {
        return ApiResponse.success(reportService.report3(categoryName));
    }
}
