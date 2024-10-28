package com.musinsa.backend.common.exception;

import com.musinsa.backend.common.ApiResponse;
import com.musinsa.backend.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ApiResponse<Void> handleException(Exception e) {
        log.error("Internal server error: {}", e.getMessage(), e);
        return ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    protected ApiResponse<Void> handleBusinessException(BusinessException e) {
        log.error("BusinessException: {}", e.getMessage());
        return ApiResponse.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation error: {}", e.getMessage());
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return ApiResponse.error(ErrorCode.BAD_REQUEST, message);
    }

    @ExceptionHandler(MyBatisSystemException.class)
    protected ApiResponse<Void> handleMyBatisSystemException(MyBatisSystemException e) {
        log.error("MyBatis system error occurred: {}", e.getMessage());
        return ApiResponse.error(ErrorCode.MYBATIS_SYSTEM_ERROR, e.getMessage());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    protected ApiResponse<Void> handleBadSqlGrammarException(BadSqlGrammarException e) {
        log.error("SQL grammar error occurred: {}", e.getMessage());
        return ApiResponse.error(ErrorCode.SQL_GRAMMAR_ERROR, e.getMessage());
    }

    @ExceptionHandler(QueryTimeoutException.class)
    protected ApiResponse<Void> handleQueryTimeoutException(QueryTimeoutException e) {
        log.error("Query timeout error occurred: {}", e.getMessage());
        return ApiResponse.error(ErrorCode.SQL_QUERY_TIMEOUT, e.getMessage());
    }
}
