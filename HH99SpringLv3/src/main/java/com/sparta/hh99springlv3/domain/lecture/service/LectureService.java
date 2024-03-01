package com.sparta.hh99springlv3.domain.lecture.service;

import com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv3.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv3.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.*;
import static com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto.*;
import static com.sparta.hh99springlv3.global.handler.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;


    public LectureResponseDto createLecture(CreateLectureRequestDto requestDto) {
        Lecture lecture = lectureRepository.save(requestDto.toEntity());
        return new LectureResponseDto();
    }

    public GetLectureResponseDto getLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(
                () -> new CustomApiException(NOT_FOUND_CATEGORY_ID.getMessage()));
        return new GetLectureResponseDto(lecture);
    }

    public GetLectureResponseDto getLectureCategory(String category) {

        return null;
    }
}
