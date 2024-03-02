package com.sparta.hh99springlv3.global.controller;

import com.sparta.hh99springlv3.global.dto.AdminRequestDto.LoginRequestDto;
import com.sparta.hh99springlv3.global.dto.AdminRequestDto.SignupRequestDto;
import com.sparta.hh99springlv3.global.dto.AdminResponseDto.LoginResponseDto;
import com.sparta.hh99springlv3.global.dto.AdminResponseDto.SignupResponseDto;
import com.sparta.hh99springlv3.global.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingresult) {
        return ResponseEntity.ok()
                .body(adminService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse res, BindingResult bindingresult) {
        return ResponseEntity.ok()
                .body(adminService.login(requestDto, res));
    }
}
