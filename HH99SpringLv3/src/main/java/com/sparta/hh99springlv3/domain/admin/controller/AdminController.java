package com.sparta.hh99springlv3.domain.admin.controller;

import com.sparta.hh99springlv3.domain.admin.dto.LoginRequestDto;
import com.sparta.hh99springlv3.domain.admin.dto.LoginResponseDto;
import com.sparta.hh99springlv3.domain.admin.service.AdminService;
import com.sparta.hh99springlv3.global.dto.ResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.sparta.hh99springlv3.domain.admin.dto.UserRequestDto.SignupRequestDto;
import static com.sparta.hh99springlv3.domain.admin.dto.UserResponseDto.SignupResponseDto;

@Controller
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<SignupResponseDto>> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        SignupResponseDto responseDto =adminService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "회원 가입 완료", responseDto));

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto, BindingResult bindingResult, HttpServletResponse response) {
        adminService.login(requestDto, response);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto<>(true, "회원 등록", response));
    }
}
