package com.example.todoappplus.controller;

import com.example.todoappplus.dto.todoDto.TodoRequestDto;
import com.example.todoappplus.dto.todoDto.TodoResponseDto;
import com.example.todoappplus.entity.Todo;
import com.example.todoappplus.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    //일정 생성하기
    @PostMapping
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto dto){
        TodoResponseDto todoResponseDto = todoService.postTodo(dto.getName(), dto.getTitle(), dto.getContents());
        return new ResponseEntity<>(todoResponseDto, HttpStatus.CREATED);
    }

    //일정 전체 조회하기
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findAllTodo(){
        List<TodoResponseDto> todos = todoService.findAllTodo();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    //회원별 일정 조회하기
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findTodoByName(@RequestBody TodoRequestDto dto){
        List<TodoResponseDto> todos = todoService.findTodoByName(dto.getName());
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
}
