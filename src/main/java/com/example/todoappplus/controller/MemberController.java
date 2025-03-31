package com.example.todoappplus.controller;
import com.example.todoappplus.dto.memberDto.*;
import com.example.todoappplus.entity.Member;
import com.example.todoappplus.service.MemberService;
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
