package com.example.todoappplus.exception;

import com.example.todoappplus.dto.errorDto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomException(CustomException ex, HttpServletRequest request) {
        Errors error = ex.getError();

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(error.getStatus().value())
                .error(error.getStatus().getReasonPhrase())
                .code(error.getCode())
                .message(error.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(error.getStatus()).body(errorResponse);
    }
    }
