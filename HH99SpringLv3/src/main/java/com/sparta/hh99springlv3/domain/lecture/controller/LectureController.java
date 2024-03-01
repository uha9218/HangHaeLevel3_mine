package com.sparta.hh99springlv3.domain.lecture.controller;

import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv3.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto.*;


@Controller
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/")
    public ResponseEntity<LectureResponseDto> createLecture(@RequestBody CreateLectureRequestDto requestDto) {
        return ResponseEntity.ok(lectureService.createLecture(requestDto));
    }

    //    @PutMapping("/{lectureId}")
//    @DeleteMapping("/{lectureId}")
    @GetMapping("/{lectureId}")
    public ResponseEntity<GetLectureResponseDto> getLecture(@PathVariable Long id) {
        GetLectureResponseDto responseDto = lectureService.getLecture(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{category}")
    public ResponseEntity<GetLectureResponseDto> getLectureCategory(@PathVariable String category) {
        GetLectureResponseDto responseDto = lectureService.getLectureCategory(category);
        return ResponseEntity.ok(responseDto);
    }
}
