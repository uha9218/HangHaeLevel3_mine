package com.sparta.hh99springlv3.domain.lecture.service;

import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.UpdateLectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.entity.Category;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv3.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv3.global.entity.AuthEnum;
import com.sparta.hh99springlv3.global.handler.exception.CustomApiException;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import static com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import static com.sparta.hh99springlv3.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import static com.sparta.hh99springlv3.global.handler.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final JwtUtil jwtUtil;

    public CreateLectureResponseDto createLecture(CreateLectureRequestDto requestDto, String tokenValue) {
        String token = jwtUtil.substringToken(tokenValue);
        isExpiredToken(token);
        Lecture lecture = lectureRepository.save(requestDto.toEntity());
        return new CreateLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public GetLectureResponseDto getLecture(Long id, String tokenValue) {
        isExpiredToken(jwtUtil.substringToken(tokenValue));
        Lecture lecture = lectureRepository.findById(id).orElseThrow(
                () -> new CustomApiException(NOT_FOUND_CATEGORY_ID.getMessage()));
        return new GetLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public List<GetLectureResponseDto> getLectureCategory(Category category, String tokenValue) {
        String token = jwtUtil.substringToken(tokenValue);
        isExpiredToken(token);
        List<Lecture> lecture = lectureRepository.findByCategory(category);
        return lecture.stream().map(GetLectureResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public GetLectureResponseDto updateLecture(Long id, UpdateLectureRequestDto requestDto, String tokenValue) {
        isExpiredToken(jwtUtil.substringToken(tokenValue));
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new CustomApiException(NOT_FOUND_ADMIN_ID.getMessage()));
        if (!checkAuthority(tokenValue)) {
            throw new CustomApiException(UNAUTHORIZED_ADMIN.getMessage());
        }
        lecture.update(requestDto);
        return new GetLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public List<GetLectureResponseDto> getLectureByTutor(Long tutorId, String tokenValue) {
        String token = jwtUtil.substringToken(tokenValue);
        isExpiredToken(token);
        return lectureRepository.findAllByTutorOrderByCreatedAtDesc(tutorId).stream().map(GetLectureResponseDto::new).toList();
    }

    private boolean checkAuthority(String tokenValue) {
        boolean isManager = true;
        String token = jwtUtil.substringToken(tokenValue);
        //권한이 Manager인지 확인
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        if (!(AuthEnum.valueOf(authority)).equals(AuthEnum.MANAGER)) {
            return !isManager;
        }
        return isManager;
    }

    private void isExpiredToken(String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }
    }
}