package org.sopt.seminar.global.error;

import lombok.extern.slf4j.Slf4j;
import org.sopt.seminar.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ApiResponse<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(">>> handle: HttpRequestMethodNotSupportedException ", e);
        return ApiResponse.failure(ErrorStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse<?>> handleBusinessException(final BusinessException e) {
        log.error(">>> handle: BusinessException ", e);
        return ApiResponse.failure(e.getErrorStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error(">>> handle: Exception ", e);
        return ApiResponse.failure(ErrorStatus.INTERNAL_SERVER_ERROR);
    }
}