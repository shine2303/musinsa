package com.musinsa.backend.controller;

import com.musinsa.backend.common.ApiResponse;
import com.musinsa.backend.dto.Search1Response;
import com.musinsa.backend.dto.Search2Response;
import com.musinsa.backend.service.SelectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/select")
@RestController
@AllArgsConstructor
public class SelectController {

    private final SelectService selectService;


    /**
     * 네이밍 : 실무에서는 이와같이 네이밍하지 않습니다. func1, exe2
     * 다만, 제가 기능별로 길게 네이밍하면 가독성이 떨어져서 이와 같이 네이밍하였습니다.
     * 참고 부탁드립니다. 네이밍은 4번 즉, admin 모듈에서 더 자세히 봐주시면 감사하겠습니다.
     */
    @GetMapping("/func1")
    @Tag(name = "select", description = "func1")
    @Operation(description = "1번 기능 조회")
    public ApiResponse<Search1Response> execute1() {
        return ApiResponse.success(selectService.search1());
    }

    @GetMapping("/func2")
    @Tag(name = "select", description = "func2")
    @Operation(description = "2번 기능 조회")
    public ApiResponse<Search2Response> execute2() {
        return ApiResponse.success(selectService.search2());
    }

}
