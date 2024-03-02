package com.sparta.hh99springlv3.domain.lecture.controller;

import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.UpdateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.entity.Category;
import com.sparta.hh99springlv3.domain.lecture.service.LectureService;
import com.sparta.hh99springlv3.global.entity.AuthEnum;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import static com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;


@RestController
@RequestMapping("/api/v1/lectures")
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
                                                               @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        GetLectureResponseDto responseDto = lectureService.updateLecture(lectureId, requestDto, tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/get/{lectureId}")
    public ResponseEntity<GetLectureResponseDto> getLecture(@PathVariable Long lectureId,
                                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue
    ) {
        GetLectureResponseDto responseDto = lectureService.getLecture(lectureId, tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<GetLectureResponseDto>> getLectureCategory(@PathVariable Category category,
                                                                          @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {

        List<GetLectureResponseDto> responseDto = lectureService.getLectureCategory(category, tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/tutors/{tutor}")
    public ResponseEntity<List<GetLectureResponseDto>> getLectureByTutor(@PathVariable Long tutor, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lectureService.getLectureByTutor(tutor, tokenValue));
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<Long> deleteLecture(@PathVariable Long lectureId, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return ResponseEntity.ok()
                .body(lectureService.deleteLecture(lectureId, tokenValue));
    }

}