package com.musinsa.backend.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 1000번대부터는 http status가 아닌 애플리케이션 레벨의 에러코드 정의
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Success
    SUCCESS(200, "SUCCESS"),

    // Client Errors : 4xx
    BAD_REQUEST(400, "잘못된 요청입니다"),
    FORBIDDEN(403, "권한이 없습니다"),
    NOT_FOUND(404, "리소스를 찾을 수 없습니다"),
    METHOD_NOT_ALLOWED(405, "허용되지 않은 메서드입니다"),

    // Server Errors : 5xx
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다"),

    // Common Errors (1000번대)

    // Brand Domain Errors (2000번대)
    BRAND_NOT_FOUND(2000, "브랜드를 찾을 수 없습니다"),
    BRAND_ALREADY_EXISTS(2001, "이미 존재하는 브랜드입니다"),
    BRAND_NAME_INVALID(2002, "브랜드 이름이 올바르지 않습니다");



    private final int code;
    private final String message;
}