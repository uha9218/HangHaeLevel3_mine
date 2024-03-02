package com.sparta.hh99springlv3.domain.tutor.service;

import com.sparta.hh99springlv3.global.entity.AuthEnum;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto.CreateTutorRequestDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto.UpdateTutorRequestDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.CreateTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.ReadTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.UpdateTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.entity.Tutor;
import com.sparta.hh99springlv3.domain.tutor.repository.TutorRepository;
import com.sparta.hh99springlv3.global.handler.exception.CustomApiException;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.hh99springlv3.global.handler.exception.ErrorCode.NOT_FOUND_TUTOR_ID;
import static com.sparta.hh99springlv3.global.handler.exception.ErrorCode.UNAUTHORIZED_ADMIN;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;
    private final JwtUtil jwtUtil;

    public boolean checkAuthority(String tokenValue) {
        boolean isMANAGER = true;
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }
        //권한이 Manager인지 확인
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        if (!(AuthEnum.valueOf(authority)).equals(AuthEnum.MANAGER)) {
            return !isMANAGER;
        }
        return isMANAGER;
    }

    public CreateTutorResponseDto createTutor(CreateTutorRequestDto requestDto, String tokenValue) {
        if (!checkAuthority(tokenValue)) {
            throw new CustomApiException(UNAUTHORIZED_ADMIN.getMessage());
        }
        Tutor tutor = tutorRepository.save(requestDto.toEntity());
        return new CreateTutorResponseDto(tutor);
    }

    @Transactional(readOnly = true)
    public ReadTutorResponseDto readTutorInfo(Long tutorId, String tokenValue) {
        if (!checkAuthority(tokenValue)) {
            throw new CustomApiException(UNAUTHORIZED_ADMIN.getMessage());
        }
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new CustomApiException(NOT_FOUND_TUTOR_ID.getMessage()));

        return new ReadTutorResponseDto(tutor);
    }

    @Transactional
    public UpdateTutorResponseDto updateTutorInfo(long tutorId, UpdateTutorRequestDto requestDto, String tokenValue) {
        if (!checkAuthority(tokenValue)) {
            throw new CustomApiException(UNAUTHORIZED_ADMIN.getMessage());
        }
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new CustomApiException(NOT_FOUND_TUTOR_ID.getMessage()));

        tutor.updateTutorInfo(requestDto);
        return new UpdateTutorResponseDto(tutor);
    }

    public long deleteTutor(long tutorId, String tokenValue) {
        if (!checkAuthority(tokenValue)) {
            throw new CustomApiException(UNAUTHORIZED_ADMIN.getMessage());
        }
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new CustomApiException(NOT_FOUND_TUTOR_ID.getMessage()));
        tutorRepository.delete(tutor);
        return tutorId;
    }
}
