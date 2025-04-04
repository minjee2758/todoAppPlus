package com.example.todoAppPlus.repository;
import com.example.todoAppPlus.entity.Todo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodoListById(Long id);

    //Optional+default로 null 방지하기
    default Todo findTodoById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("ID " + id + "에 해당하는 Todo가 없습니다."));
    }
}
