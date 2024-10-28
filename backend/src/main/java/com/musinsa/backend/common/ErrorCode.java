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

    // Common Errors (1000번대, 요구사항)
    NON_RESULT_ERROR(1000, "조회 결과가 존재하지 않습니다."),


    // DB Errors (2000번대)
    DB_ACCESS_ERROR(2000, "DB에서 데이터 엑세스 중 오류가 발생했습니다."),
    MYBATIS_SYSTEM_ERROR(2001, "mybatis 시스템 오류가 발생했습니다."),
    SQL_GRAMMAR_ERROR(2002, "sql 문법 오류입니다."),
    SQL_QUERY_TIMEOUT(2003, "sql 쿼리 timeout 발생했습니다.");


    private final int code;
    private final String message;
}
