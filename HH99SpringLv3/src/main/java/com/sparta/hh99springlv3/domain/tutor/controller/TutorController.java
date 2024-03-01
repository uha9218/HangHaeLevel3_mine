package com.sparta.hh99springlv3.domain.tutor.controller;

import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto.CreateTutorRequestDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.ReadTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.CreateTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.service.TutorService;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tutors")
@RequiredArgsConstructor
public class TutorController {
    private final TutorService tutorService;
    @PostMapping("/")
    public CreateTutorResponseDto createTutor(@RequestBody @Valid CreateTutorRequestDto requestDto, BindingResult bindingResult,@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue ){
        return tutorService.createTutor(requestDto, tokenValue);
    }

    @GetMapping("/{tutorId}")
    public ReadTutorResponseDto readTutorInfo(@PathVariable Long tutorId, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return tutorService.readTutorInfo(tutorId, tokenValue);
    }
}
