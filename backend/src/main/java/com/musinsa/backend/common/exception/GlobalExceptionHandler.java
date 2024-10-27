package com.musinsa.backend.common.exception;

import com.musinsa.backend.common.ApiResponse;
import com.musinsa.backend.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
}