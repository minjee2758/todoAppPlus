package com.example.todoAppPlusLv6.service;

import com.example.todoAppPlusLv6.dto.memberDto.MemberResponseDto;
import com.example.todoAppPlusLv6.dto.memberDto.SignUpResponseDto;
import com.example.todoAppPlusLv6.entity.Member;
import com.example.todoAppPlusLv6.exception.CustomException;
import com.example.todoAppPlusLv6.exception.Errors;
import com.example.todoAppPlusLv6.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //이메일과 기존 비번 바꿀 비번을 입력해, 만약 기존 비번이 일치하면 비번 바꿔주기
    public boolean updateMemberPassword(String email, String oldPassword,String newPassword) {
        String findPw = memberRepository.findMemberdByEmail(email).get().getPassword();
        Member member = memberRepository.findMemberdByEmail(email).get();
        if (findPw.equals(oldPassword)){
            member.updatePassword(newPassword);
            memberRepository.save(member);
            return true;
        } else {
            return false;
        }
    }

    //회원가입된 회원이 맞는지 검증
    public Member login(String email, String password) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findMemberdByEmail(email).orElseThrow(() -> new CustomException(Errors.INVALID_LOGIN)));
        if (member.get().getPassword().equals(password)) {
            return member.get();
        } else {
            throw new CustomException(Errors.INVALID_LOGIN);
        }
    }
}
