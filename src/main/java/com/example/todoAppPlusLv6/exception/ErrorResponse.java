package com.example.todoAppPlusLv6.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<FieldError> fieldErrors;

    public static ErrorResponse of(Errors error) {
        return ErrorResponse.builder()
                .status(error.getStatus())
                .error(error.getError())
                .code(error.getCode())
                .message(error.getMessage())
                .fieldErrors(new ArrayList<>())
                .build();
    }

    public static ErrorResponse of(Errors error, String message) {
        return ErrorResponse.builder()
                .status(error.getStatus())
                .error(error.getError())
                .code(error.getCode())
                .message(message)
                .fieldErrors(new ArrayList<>())
                .build();
    }

    public static ErrorResponse of(Errors error, List<FieldError> fieldErrors) {
        return ErrorResponse.builder()
                .status(error.getStatus())
                .error(error.getError())
                .code(error.getCode())
                .message(error.getMessage())
                .fieldErrors(fieldErrors)
                .build();
    }

    @Getter
    @Builder
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public static FieldError of(String field, String value, String reason) {
            return FieldError.builder()
                    .field(field)
                    .value(value)
                    .reason(reason)
                    .build();
        }
    }
}