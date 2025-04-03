package com.example.todoappplus.exception;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Errors {
    INVALID_LOGIN(401 , "Bad Request","C001", "아이디 또는 비밀번호가 일치하지 않습니다."),
    REQUIRED_USEREMAIL(400,"Bad Request","C002", "이메일 입력은 필수입니다."),
    INVALID_INPUT_VALUE(400, "", "C003", "입력값이 잘못되었습니다");

    private final int status;
    private final String error;
    private final String code;
    private final String message;

}
