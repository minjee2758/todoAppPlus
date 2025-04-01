package com.example.todoappplus.service;

import com.example.todoappplus.dto.todoDto.TodoResponseDto;
import com.example.todoappplus.entity.Member;
import com.example.todoappplus.entity.Todo;
import com.example.todoappplus.repository.MemberRepository;
import com.example.todoappplus.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    //일정 생성하기
    public TodoResponseDto postTodo(String name, String title, String contents) {
        Member member = memberRepository.findMemberByNameOrElseThrow(name);
        Todo todo = new Todo(title, contents);
        todo.setMember(member);

        todoRepository.save(todo);
        return new TodoResponseDto(name, title, contents);
    }

    //전체 일정 찾기 - 사용자 이름과 함께
    public List<TodoResponseDto> findAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> new TodoResponseDto(todo.getTitle(),todo.getContent(), todo.getMember().getName())).toList();
    }
}
