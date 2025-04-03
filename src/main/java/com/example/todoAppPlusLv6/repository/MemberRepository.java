package com.example.todoAppPlusLv6.repository;

import com.example.todoAppPlusLv6.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findMemberByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "입력하신 id의 회원은 존재하지 않습니다"));
    }

    //이메일주소로 회원조회하기
    Optional<Member> findMemberdByEmail(String email);

    Optional<Member> findMemberByName(String name);

    default Member findMemberByNameOrElseThrow(String name){
        return findMemberByName(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다"));
    };
}
