package com.sparta.hh99springlv3.domain.admin.service;

import com.sparta.hh99springlv3.domain.admin.dto.AdminRequestDto.SignupRequestDto;
import com.sparta.hh99springlv3.domain.admin.dto.AdminResponseDto.SignupResponseDto;
import com.sparta.hh99springlv3.domain.admin.entity.Admin;
import com.sparta.hh99springlv3.domain.admin.entity.AuthEnum;
import com.sparta.hh99springlv3.domain.admin.entity.Dept;
import com.sparta.hh99springlv3.domain.admin.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sparta.hh99springlv3.domain.admin.entity.AuthEnum.MANAGER;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
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
}
