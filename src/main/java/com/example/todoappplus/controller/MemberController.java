package com.example.todoappplus.controller;
import com.example.todoappplus.common.Const;
import com.example.todoappplus.dto.memberDto.*;
import com.example.todoappplus.entity.Member;
import com.example.todoappplus.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members") //prefix
@RequiredArgsConstructor //생성자 알아서 만들기
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(dto.getName(), dto.getPassword(), dto.getEmail());
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberRequestDto dto,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {

        Member member = memberService.login(dto.getEmail(), dto.getPassword()); //회원이 존재하는지 검증
        HttpSession session = request.getSession(); //세션 요청하기
        session.setAttribute(Const.LOGIN_USER, member.getId());

        return ResponseEntity.ok("로그인 성공");
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
            return ResponseEntity.ok("로그아웃 성공");
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //모든 회원 조회하기
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAllMember() {
        List<MemberResponseDto> members = memberService.findAllMember();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    //회원 단건 조회 by ID
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findMemberById(@PathVariable Long id){
        MemberResponseDto memberResponseDto = memberService.findMemberById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    //회원 비번 수정 by 이메일 & 비번
    @PutMapping
    public ResponseEntity<Boolean> updateMemberPassword(@RequestBody UpdatePasswordResponseDto dto){
        boolean result = memberService.updateMemberPassword(dto.getEmail(), dto.getOldPassword(), dto.getNewPassword());
        if (result){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //회원 정보 삭제 by 이메일&비번
    @DeleteMapping
    public ResponseEntity<Boolean> deleteMemberById(@RequestBody MemberRequestDto dto){
        boolean result = memberService.delete(dto.getEmail(), dto.getPassword());
        if (result){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
