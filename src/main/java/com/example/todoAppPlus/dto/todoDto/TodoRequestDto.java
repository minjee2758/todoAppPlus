package com.example.todoAppPlus.dto.todoDto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private final String title;
    private final String content;
    private final String name;
    private final String email;
    private final String password;
    private final Long todoId;

    public TodoRequestDto(String title, String content, String name, String email, String password, Long todoId) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.email = email;
        this.password = password;
        this.todoId = todoId;
    }
}