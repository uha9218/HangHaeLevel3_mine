package com.sparta.hh99springlv3.global.dto;

import com.sparta.hh99springlv3.global.entity.Admin;
import com.sparta.hh99springlv3.global.entity.AuthEnum;
import com.sparta.hh99springlv3.global.entity.Dept;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequestDto {
        @Schema(description = "관리자 이메일", example = "example1@example.com")
        @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
        private String email;
        @Schema(description = "비밀 번호", example = "Password123~")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
        private String password;
        @Schema(description = "관리자 부서", example = "DEVELOPER")
        private Dept dept;
        @Schema(description = "매니저 확인", example = "true")
        private boolean auth = false;
        private String adminToken = "";

        public Admin toEntity(AuthEnum auth, String password) {
            return Admin.builder()
                    .email(email)
                    .password(password)
                    .dept(dept)
                    .Authority(auth)
                    .build();
        }
    }

    @Getter
    public static class LoginRequestDto {
        @Schema(description = "관리자 이메일", example = "example1@example.com")
        private String email;
        @Schema(description = "비밀번호", example = "Password123~")
        private String password;

        public Admin toEntity() {
            return Admin.builder()
                    .email(email)
                    .password(password)
                    .build();
        }
    }
}
