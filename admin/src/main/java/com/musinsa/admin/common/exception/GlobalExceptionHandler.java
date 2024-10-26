package com.musinsa.admin.common.exception;

import com.musinsa.admin.common.ApiResponse;
import com.musinsa.admin.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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

    @ExceptionHandler(Exception.class)
    protected ApiResponse<Void> handleException(Exception e) {
        log.error("Internal server error: {}", e.getMessage(), e);
        return ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ApiResponse<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("JSON parse error: {}", e.getMessage());
        String  message = "잘못된 입력값입니다. 입력값을 확인해주세요.";
        return ApiResponse.error(ErrorCode.BAD_REQUEST, message);
    }
}