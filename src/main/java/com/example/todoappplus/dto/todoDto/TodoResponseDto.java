package com.example.todoappplus.dto.todoDto;

import lombok.Getter;

@Getter
public class TodoResponseDto {
    private final String title;
    private final String contents;
    private final String name;

    public TodoResponseDto(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }
}
