package com.example.todoAppPlusLv6.controller;

import com.example.todoAppPlusLv6.dto.todoDto.TodoRequestDto;
import com.example.todoAppPlusLv6.dto.todoDto.TodoResponseDto;
import com.example.todoAppPlusLv6.dto.todoDto.UpdateTodoResponseDto;
import com.example.todoAppPlusLv6.service.TodoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<TodoResponseDto> postTodo(@Valid @RequestBody TodoRequestDto dto, Error error){
        TodoResponseDto todoResponseDto = todoService.postTodo(dto.getName(), dto.getTitle(), dto.getContent());
        return new ResponseEntity<>(todoResponseDto, HttpStatus.CREATED);
    }

    //일정 전체 조회하기
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findAllTodo(){
        List<TodoResponseDto> todos = todoService.findAllTodo();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    //회원별 일정 조회하기 - 이름으로 조회가능 url = "/todo/name"
    @GetMapping("/name")
    public ResponseEntity<List<TodoResponseDto>> findTodoByName(@RequestBody TodoRequestDto dto){
        List<TodoResponseDto> todos = todoService.findTodoByName(dto.getName());
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    //일정 수정하기 - url= "/todo/update"
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateTodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto dto){
        UpdateTodoResponseDto updateTodoResponseDto = todoService.updateTodo(dto.getEmail(), dto.getPassword(), id, dto.getContent());
        return new ResponseEntity<>(updateTodoResponseDto, HttpStatus.OK);
    }

    //일정 삭제하기 - url = "/todo/delete"
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTodo(@RequestBody TodoRequestDto dto, @PathVariable Long id){
        boolean result = todoService.deleteTodo(dto.getEmail(), dto.getPassword(), id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}
