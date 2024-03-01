package com.sparta.hh99springlv3.domain.admin.controller;

import com.sparta.hh99springlv3.domain.admin.dto.AdminRequestDto.LoginRequestDto;
import com.sparta.hh99springlv3.domain.admin.dto.AdminRequestDto.SignupRequestDto;
import com.sparta.hh99springlv3.domain.admin.dto.AdminResponseDto.LoginResponseDto;
import com.sparta.hh99springlv3.domain.admin.dto.AdminResponseDto.SignupResponseDto;
import com.sparta.hh99springlv3.domain.admin.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public SignupResponseDto signup (@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingresult){
        SignupResponseDto responseDto = adminService.signup(requestDto);
        return responseDto;
    }

    @PostMapping("/login")
    public LoginResponseDto login (@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse res, BindingResult bindingresult){
        LoginResponseDto responseDto = adminService.login(requestDto, res);
        return responseDto;
    }
}
