package com.sparta.hh99springlv3.domain.lecture.controller;

import com.sparta.hh99springlv3.domain.admin.entity.AuthEnum;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.UpdateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv3.domain.lecture.service.LectureService;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto.*;


@Controller
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    @PostMapping("/")
    public ResponseEntity<LectureResponseDto> createLecture(@RequestBody @Valid CreateLectureRequestDto requestDto,
                                                            BindingResult bindingResult,
                                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return ResponseEntity.ok(lectureService.createLecture(requestDto, tokenValue));
    }
    @Secured(AuthEnum.Authority.MANAGER)
    @PutMapping("/{lectureId}")
    public GetLectureResponseDto updateLecture(@PathVariable Long lectureId, @RequestBody UpdateLectureRequestDto requestDto,
                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return lectureService.updateLecture(lectureId, requestDto, tokenValue);
    }
    @GetMapping("/get/{lectureId}")
    public GetLectureResponseDto getLecture(@PathVariable Long lectureId,
                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue
                                            ) {
        GetLectureResponseDto responseDto = lectureService.getLecture(lectureId,tokenValue);
        return responseDto;
    }

    @GetMapping("/{category}")
    public List<GetLectureResponseDto> getLectureCategory(@PathVariable String category,
                                                          @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {

        List<GetLectureResponseDto> responseDto = lectureService.getLectureCategory(category,tokenValue);
        return ResponseEntity.ok(responseDto).getBody();
    }
}