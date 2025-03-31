package com.example.todoappplus.repository;

import com.example.todoappplus.dto.memberDto.MemberResponseDto;
import com.example.todoappplus.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findMemberByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "입력하신 id의 회원은 존재하지 않습니다"));
    }
}
