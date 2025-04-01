package com.example.todoappplus.repository;


import com.example.todoappplus.entity.Member;
import com.example.todoappplus.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodoById(Long memberid);
}
