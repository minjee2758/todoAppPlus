package com.example.todoappplus.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Custom Exception 처리 - Service 계층에서 발생한 비즈니스 예외 처리
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException: {}", e.getMessage());
        Errors errorCode = e.getError();
        ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error("MethodArgumentNotValidException: {}", e.getMessage());
//        List<ErrorResponse.FieldError> fieldErrors = processFieldErrors(e.getBindingResult());
//        ErrorResponse response = ErrorResponse.of(Errors.INVALID_INPUT_VALUE, fieldErrors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
    }
