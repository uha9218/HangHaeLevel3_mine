package com.sparta.hh99springlv3.global.service;

import com.sparta.hh99springlv3.global.dto.AdminRequestDto.LoginRequestDto;
import com.sparta.hh99springlv3.global.dto.AdminRequestDto.SignupRequestDto;
import com.sparta.hh99springlv3.global.dto.AdminResponseDto.LoginResponseDto;
import com.sparta.hh99springlv3.global.dto.AdminResponseDto.SignupResponseDto;
import com.sparta.hh99springlv3.global.entity.Admin;
import com.sparta.hh99springlv3.global.entity.AuthEnum;
import com.sparta.hh99springlv3.global.entity.Dept;
import com.sparta.hh99springlv3.global.jwt.JwtUtil;
import com.sparta.hh99springlv3.global.repository.AdminRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public SignupResponseDto signup(SignupRequestDto requestDto) {

        String password = passwordEncoder.encode(requestDto.getPassword());

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<Admin> checkEmail = adminRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }


        // 사용자 ROLE 확인
        AuthEnum auth = AuthEnum.STAFF;
        if (requestDto.isAuth() && !requestDto.getDept().equals(Dept.MARKETING)) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            auth = AuthEnum.MANAGER;
        }
        // 사용자 등록
        Admin admin = adminRepository.save(requestDto.toEntity(auth, password));
        return new SignupResponseDto(admin);
    }

    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse res) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 사용자 확인
        Admin admin = adminRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(admin.getAdminId(), admin.getAuthority());
        jwtUtil.addJwtToCookie(token, res);

        return new LoginResponseDto(admin);
    }
}
