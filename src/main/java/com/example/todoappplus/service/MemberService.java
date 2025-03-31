package com.example.todoappplus.service;

import com.example.todoappplus.dto.memberDto.MemberResponseDto;
import com.example.todoappplus.dto.memberDto.SignUpResponseDto;
import com.example.todoappplus.entity.Member;
import com.example.todoappplus.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String name, String password, String email) {
        Member member = new Member(name, password, email);
        Member savedMember = memberRepository.save(member);
        return new SignUpResponseDto(savedMember.getId(), savedMember.getName(), savedMember.getEmail());
    };

    public List<MemberResponseDto> findAllMember() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> new MemberResponseDto(member.getId(), member.getName(), member.getEmail())).toList();
    }

    //id로 단건조회
    public MemberResponseDto findMemberById(Long id) {
        Member member = memberRepository.findMemberByIdOrElseThrow(id);
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail());
    }

    //이메일을 통해 멤버 조회 후, 비밀번호가 맞으면 삭제 로직 수행
    public boolean delete(String email, String password) {
        String findPw = memberRepository.findMemberdByEmail(email).get().getPassword();
        if (findPw.equals(password)){
            memberRepository.delete(memberRepository.findMemberdByEmail(email).get());
            return true;
        } else {
            return false;
        }
    }
}
