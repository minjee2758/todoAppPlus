package com.example.todoAppPlusLv6.dto.memberDto;

import lombok.Getter;

@Getter
public class MemberRequestDto {
    private final String name;
    private final String email;
    private final String password;

    public MemberRequestDto(String name, String email, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
