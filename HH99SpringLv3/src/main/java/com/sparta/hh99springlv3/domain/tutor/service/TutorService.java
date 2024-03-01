package com.sparta.hh99springlv3.domain.tutor.service;

import com.sparta.hh99springlv3.domain.admin.entity.AuthEnum;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto.CreateTutorRequestDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.dto.TutorResponseDto.CreateTutorResponseDto;
import com.sparta.hh99springlv3.domain.tutor.entity.Tutor;
import com.sparta.hh99springlv3.domain.tutor.repository.TutorRepository;
import com.sparta.hh99springlv3.global.handler.exception.CustomApiException;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sparta.hh99springlv3.global.handler.exception.ErrorCode.UNAUTHORIZED_ADMIN;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;
    private final JwtUtil jwtUtil;
    public CreateTutorResponseDto createTutor(CreateTutorRequestDto requestDto, String tokenValue){
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }
        //권한이 Manager인지 확인
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        if(!(AuthEnum.valueOf(authority)).equals(AuthEnum.MANAGER)){
            throw new CustomApiException(UNAUTHORIZED_ADMIN.getMessage());
        }

        Tutor tutor = tutorRepository.save(requestDto.toEntity());
        return new CreateTutorResponseDto(tutor);
    }
}
