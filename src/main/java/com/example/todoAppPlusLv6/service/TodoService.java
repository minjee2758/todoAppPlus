package com.example.todoAppPlusLv6.service;

import com.example.todoAppPlusLv6.config.PasswordEncoder;
import com.example.todoAppPlusLv6.dto.todoDto.TodoResponseDto;
import com.example.todoAppPlusLv6.dto.todoDto.UpdateTodoResponseDto;
import com.example.todoAppPlusLv6.entity.Member;
import com.example.todoAppPlusLv6.entity.Todo;
import com.example.todoAppPlusLv6.repository.MemberRepository;
import com.example.todoAppPlusLv6.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //일정 생성하기
    public TodoResponseDto postTodo(String name, String title, String content) {
        Member member = memberRepository.findMemberByNameOrElseThrow(name);
        Todo todo = new Todo(title, content);
        todo.setMember(member);

        todoRepository.save(todo);
        System.out.println(name+"의 저장된 todo title : " +title + "| content : " + content);
        return new TodoResponseDto(name, title, content);
    }

    //전체 일정 찾기 - 사용자 이름과 함께
    public List<TodoResponseDto> findAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> new TodoResponseDto(todo.getMember().getName(),todo.getTitle(), todo.getContent())).toList();
    }

    //사용자 이름별로 할일 찾기
    public List<TodoResponseDto> findTodoByName(String name) {
        Long memberid = memberRepository.findMemberByNameOrElseThrow(name).getId();
        List<Todo> todos = todoRepository.findTodoListById(memberid);
        return todos.stream().map(todo -> new TodoResponseDto(todo.getTitle(), todo.getContent(), todo.getMember().getName())).toList();
    }

    //사용자의 email과 password를 입력받아, 만약 비번이 맞으면 해당하는 id의 할일 삭제하기
    public boolean deleteTodo(String email, String password, Long id) {
        //비번 검증
        String savedMemberPw = memberRepository.findMemberdByEmail(email).get().getPassword();
        boolean isSame = passwordEncoder.matches(password, savedMemberPw);
        if (isSame) {
            Todo todo = todoRepository.findTodoById(id);
            todoRepository.delete(todo);
            System.out.println(" 할일 id : "+id+" 가 삭제되었습니다");
            return true;
        } else {
            return false;
        }
    }

    //이메일과 비번으로 사용자 조회, 만약 일치하면 해당하는 id의 할일의 content 내용을 수정
    public UpdateTodoResponseDto updateTodo(String email, String password, Long todoId, String content) {
        //비번 검증
        String savedMemberPw = memberRepository.findMemberdByEmail(email).get().getPassword();
        boolean isSame = passwordEncoder.matches(password, savedMemberPw);
        if (isSame) {
            //해당하는 id의 할일 찾기
            Todo todo = todoRepository.findTodoById(todoId);
            todo.updateContent(content);
            todoRepository.save(todo);
            System.out.println("Updated content: " + todo.getContent());
            return new UpdateTodoResponseDto(todo.getTitle(), todo.getContent(), todo.getMember().getName());
        } else {
            //이렇게 null을 보내도 되는감???/
            return null;
        }
    }

}
