package com.sparta.hh99springlv3.domain.lecture.controller;

import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.UpdateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.entity.Category;
import com.sparta.hh99springlv3.domain.lecture.service.LectureService;
import com.sparta.hh99springlv3.global.entity.AuthEnum;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Lecture API",description = "강의 관련 API 내용입니다.")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/")
    @Operation(summary = "createLecture",description = "관리자가 강의를 등록한다.")
    @ApiResponse(responseCode = "201", description = "강의 등록 성공", content = @Content(schema = @Schema(implementation = CreateLectureResponseDto.class)))
    public ResponseEntity<CreateLectureResponseDto> createLecture(@RequestBody @Valid CreateLectureRequestDto requestDto,
                                                                  @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        CreateLectureResponseDto responseDto = lectureService.createLecture(requestDto, tokenValue);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Secured(AuthEnum.Authority.MANAGER)
    @PutMapping("/{lectureId}")
    @Operation(summary = "updateLecture",description = "매니저가 강의를 수정한다, Manager권한 필요")
    @ApiResponse(responseCode = "200", description = "강의 수정 성공", content = @Content(schema = @Schema(implementation = GetLectureResponseDto.class)))
    public ResponseEntity<GetLectureResponseDto> updateLecture(@PathVariable Long lectureId, @RequestBody UpdateLectureRequestDto requestDto,
                                                               @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        GetLectureResponseDto responseDto = lectureService.updateLecture(lectureId, requestDto, tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/get/{lectureId}")
    @Operation(summary = "getLecture",description = "카테고리별 강의를 조회한다.")
    @ApiResponse(responseCode = "200", description = "카테고리별 강의 조회 성공", content = @Content(schema = @Schema(implementation = GetLectureResponseDto.class)))
    public ResponseEntity<GetLectureResponseDto> getLecture(@PathVariable Long lectureId,
                                                            @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue
    ) {
        GetLectureResponseDto responseDto = lectureService.getLecture(lectureId, tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "getLectureCategory",description = "관리자가 강의를 등록한다.")
    @ApiResponse(responseCode = "200", description = "카테고리별 강의 조회 성공", content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<GetLectureResponseDto>> getLectureCategory(@PathVariable Category category,
                                                                          @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {

        List<GetLectureResponseDto> responseDto = lectureService.getLectureCategory(category, tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/tutors/{tutor}")
    @Operation(summary = "getLectureByTutor",description = "강사별 강의를 조회한다.")
    @ApiResponse(responseCode = "200", description = "강사별 강의 조회 성공", content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<GetLectureResponseDto>> getLectureByTutor(@PathVariable Long tutor, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lectureService.getLectureByTutor(tutor, tokenValue));
    }

    @DeleteMapping("/{lectureId}")
    @Operation(summary = "deleteLecture",description = "매니저가 강의를 삭제한다, Manager권한 필요")
    @ApiResponse(responseCode = "200", description = "강의 삭제 성공", content = @Content(schema = @Schema(implementation = Long.class)))
    public ResponseEntity<Long> deleteLecture(@PathVariable Long lectureId, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return ResponseEntity.ok()
                .body(lectureService.deleteLecture(lectureId, tokenValue));
    }

}