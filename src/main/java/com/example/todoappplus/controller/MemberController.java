package com.example.todoappplus.controller;
import com.example.todoappplus.dto.memberDto.MemberResponseDto;
import com.example.todoappplus.dto.memberDto.SignUpRequestDto;
import com.example.todoappplus.dto.memberDto.SignUpResponseDto;
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
}
