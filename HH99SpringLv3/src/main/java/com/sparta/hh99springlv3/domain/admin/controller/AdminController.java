package com.sparta.hh99springlv3.domain.admin.controller;

import com.sparta.hh99springlv3.domain.admin.dto.AdminResponseDto;
import com.sparta.hh99springlv3.domain.admin.dto.AdminResponseDto.SignupResponseDto;
import com.sparta.hh99springlv3.domain.admin.dto.AdminRequestDto.SignupRequestDto;
import com.sparta.hh99springlv3.domain.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sparta.hh99springlv3.domain.admin.entity.Admin;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/")
    public SignupResponseDto signup (@RequestBody @Valid SignupRequestDto requestDto){
        SignupResponseDto responseDto = adminService.signup(requestDto);
        return responseDto;
    }
}
