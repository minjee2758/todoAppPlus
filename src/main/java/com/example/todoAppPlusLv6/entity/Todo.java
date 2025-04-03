package com.example.todoAppPlusLv6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "todo")
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @NotBlank(message = "제목은 공백일 수 없습니다")
    @Size(max = 2, message = "제목은 최대 10글자로 작성할 수 있습니다")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "내용은 공백이라도 존재해야합니다")
    @Column(columnDefinition = "longtext")
    private String content;


    public Todo(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Todo(){}


    public void setMember(Member member){
        this.member = member;
    }

    public void updateContent(String content){
        this.content = content;
    }

}
