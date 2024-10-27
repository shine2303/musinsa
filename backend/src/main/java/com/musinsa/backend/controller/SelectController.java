package com.musinsa.backend.controller;

import com.musinsa.backend.dto.Search1Response;
import com.musinsa.backend.service.SelectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/select")
@RestController
@AllArgsConstructor
public class SelectController {

    private final SelectService selectService;

    @GetMapping("/func1")
    @Tag(name = "select", description = "func1")
    @Operation(description = "1번 기능 조회")
    public ResponseEntity<Search1Response> execute() {

        return ResponseEntity.ok(selectService.search1());
    }

}
