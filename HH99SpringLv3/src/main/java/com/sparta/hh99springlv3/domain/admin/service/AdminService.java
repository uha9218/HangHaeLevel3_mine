package com.sparta.hh99springlv3.domain.admin.service;

import com.sparta.hh99springlv3.domain.admin.dto.LoginRequestDto;
import com.sparta.hh99springlv3.domain.admin.entity.Admin;
import com.sparta.hh99springlv3.domain.admin.entity.AdminRoleEnum;
import com.sparta.hh99springlv3.domain.admin.repository.AdminRepository;
import com.sparta.hh99springlv3.global.handler.exception.CustomApiException;
import com.sparta.hh99springlv3.global.handler.exception.CustomValidationException;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.sparta.hh99springlv3.domain.admin.dto.UserRequestDto.SignupRequestDto;
import static com.sparta.hh99springlv3.domain.admin.dto.UserResponseDto.SignupResponseDto;
import static com.sparta.hh99springlv3.global.handler.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new CustomApiException(NOT_FOUND_ADMIN_ID.getMessage()));
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new CustomApiException(NOT_FOUND_ADMIN_PW.getMessage());
        }

        String token = jwtUtil.createToken(admin.getUsername(), admin.getRole());
        jwtUtil.addJwtToCookie(token, response);
    }

    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        Admin findAdmin = adminRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("g2"));
        adminRepository.findByEmail(email).orElseThrow(() -> new CustomValidationException(ALREADY_REGISTERED_EMAIL.getMessage(),Map.of("email","이미 등록된 이메일 입니다.")));

        AdminRoleEnum manager = requestDto.isManager()? AdminRoleEnum.MANAGER : AdminRoleEnum.ADMIN;
        if (requestDto.isManager()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new CustomValidationException(TOKEN_NOT_VALID.getMessage(),Map.of("adminToken","유효하지 않은 토큰입니다."));
            }
        }

        Admin admin = adminRepository.save(requestDto.toEntity());
        return new SignupResponseDto(admin);

    }
}
