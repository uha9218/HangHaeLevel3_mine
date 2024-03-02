package com.sparta.hh99springlv3.domain.lecture.controller;

import com.sparta.hh99springlv3.domain.admin.entity.AuthEnum;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.UpdateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv3.domain.lecture.service.LectureService;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CreateLectureResponseDto> createLecture(@RequestBody @Valid CreateLectureRequestDto requestDto,
                                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        CreateLectureResponseDto responseDto = lectureService.createLecture(requestDto, tokenValue);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @Secured(AuthEnum.Authority.MANAGER)
    @PutMapping("/{lectureId}")
    public ResponseEntity<GetLectureResponseDto> updateLecture(@PathVariable Long lectureId, @RequestBody UpdateLectureRequestDto requestDto,
                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        GetLectureResponseDto responseDto = lectureService.updateLecture(lectureId, requestDto, tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/get/{lectureId}")
    public ResponseEntity<GetLectureResponseDto> getLecture(@PathVariable Long lectureId,
                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue
                                            ) {
        GetLectureResponseDto responseDto = lectureService.getLecture(lectureId,tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<GetLectureResponseDto>> getLectureCategory(@PathVariable String category,
                                                          @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {

        List<GetLectureResponseDto> responseDto = lectureService.getLectureCategory(category,tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}