package com.example.todoAppPlus.dto.todoDto;

import lombok.Getter;

@Getter
public class UpdateTodoResponseDto {
    private final String title;
    private final String contents;
    private final String name;


    public UpdateTodoResponseDto(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }
}
