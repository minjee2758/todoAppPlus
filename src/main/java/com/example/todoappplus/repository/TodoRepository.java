package com.example.todoappplus.repository;


import com.example.todoappplus.entity.Member;
import com.example.todoappplus.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
