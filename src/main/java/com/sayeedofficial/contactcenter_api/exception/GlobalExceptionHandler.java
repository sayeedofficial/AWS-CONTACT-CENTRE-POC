package com.sayeedofficial.contactcenter_api.exception;

import com.sayeedofficial.contactcenter_api.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(DuplicateUserException.class)
        public ResponseEntity<ApiErrorResponse> handleDuplicateUserException(
                        DuplicateUserException exception, HttpServletRequest request) {

                ApiErrorResponse errorResponse = new ApiErrorResponse(Instant.now(),
                                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
                                exception.getMessage(), Collections.emptyMap(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiErrorResponse> handleValidationException(
                        MethodArgumentNotValidException exception, HttpServletRequest request) {

                Map<String, String> fieldErrors = new LinkedHashMap<>();

                exception.getBindingResult().getFieldErrors().forEach(fieldError -> fieldErrors
                                .put(fieldError.getField(), fieldError.getDefaultMessage()));

                ApiErrorResponse errorResponse = new ApiErrorResponse(Instant.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                "Request validation failed", fieldErrors, request.getRequestURI());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
}
