package com.example.todoAppPlusLv6.service;

import com.example.todoAppPlusLv6.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(String name, String password, String email) {
        String encodePassword = passwordEncoder.encode(password);//비밀번호 해쉬키로 암호화하기
        Member member = new Member(name, encodePassword, email);
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
        boolean isSamePw = passwordEncoder.matches(password, findPw);
        if (isSamePw){
            memberRepository.delete(memberRepository.findMemberdByEmail(email).get());
            return true;
        } else {
            return false;
        }
    }

    //이메일과 기존 비번 바꿀 비번을 입력해, 만약 기존 비번이 일치하면 비번 바꿔주기
    public boolean updateMemberPassword(String email, String oldPassword,String newPassword) {
        String findPw = memberRepository.findMemberdByEmail(email).get().getPassword();
        boolean isSame = passwordEncoder.matches( oldPassword, findPw);
        Member member = memberRepository.findMemberdByEmail(email).get();
        if (isSame){
            String encodePassword = passwordEncoder.encode(newPassword); //새로운 비번도 인코딩해주기
            member.updatePassword(encodePassword);
            memberRepository.save(member);
            return true;
        } else {
            return false;
        }
    }

    //회원가입된 회원이 맞는지 검증
    public Member login(String email, String password) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findMemberdByEmail(email).orElseThrow(()
                -> new CustomException(Errors.INVALID_LOGIN)));

        if (passwordEncoder.matches(password, member.get().getPassword())) { //인코딩한 비번과 매칭해서 맞는지 확인하기
            return member.get();
        } else {
            throw new CustomException(Errors.INVALID_LOGIN);
        }
    }
}
