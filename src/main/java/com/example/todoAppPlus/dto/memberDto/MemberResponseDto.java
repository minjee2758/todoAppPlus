package com.example.todoAppPlus.dto.memberDto;

import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final Long id;
    private final String name;
    private final String email;

    public MemberResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
