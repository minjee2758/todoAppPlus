package com.example.todoappplus.dto.memberDto;

import lombok.Getter;

@Getter
public class UpdatePasswordResponseDto {
    private final String email;
    private final String name;
    private final String oldPassword;
    private final String newPassword;

    public UpdatePasswordResponseDto(String email, String name, String oldPassword, String newPassword) {
        this.email = email;
        this.name = name;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
