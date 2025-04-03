package com.example.todoappplus.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final Errors error;

    public CustomException(Errors error) {
        super(error.getMessage());
        this.error = error;
    }
}
