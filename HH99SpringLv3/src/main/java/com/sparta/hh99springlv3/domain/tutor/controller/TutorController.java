package com.sparta.hh99springlv3.domain.tutor.controller;

import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto.CreateTutorRequestDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.ReadTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.CreateTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.UpdateTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto.UpdateTutorRequestDto;
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

    @PutMapping("/{tutorId}")
    public UpdateTutorResponseDto updateTutorInfo(@PathVariable Long tutorId, @RequestBody UpdateTutorRequestDto requestDto,@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return tutorService.updateTutorInfo(tutorId, requestDto, tokenValue);
    }

    @DeleteMapping("/{tutorId}")
    public long deleteTutor(@PathVariable Long tutorId, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return tutorService.deleteTutor(tutorId,tokenValue);
    }
}
