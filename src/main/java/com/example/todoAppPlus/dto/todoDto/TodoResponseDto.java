package com.example.todoAppPlus.dto.todoDto;

import lombok.Getter;

@Getter
public class TodoResponseDto {
    private final String title;
    private final String content;
    private final String name;

    public TodoResponseDto(String name, String title, String content) {
        this.title = title;
        this.content = content;
        this.name = name;
    }
}
