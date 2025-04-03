package com.example.todoAppPlus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 공백일 수 없습니다. 4글자 이하로 입력하세요")
    @Size(min = 2, message = "2글자 이상 입력하세요")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank //공백도 안됨
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{4,10}"
            , message = "비밀번호는 4~10자, 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @Column(nullable = false)
    private String password;


    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,20}$"
            , message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 공백일 수 없습니다")
    @Column(nullable = false, unique = true)
    private String email;

    public Member(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Member() {}

    public void updatePassword(String password){
        this.password = password;
    }
}
