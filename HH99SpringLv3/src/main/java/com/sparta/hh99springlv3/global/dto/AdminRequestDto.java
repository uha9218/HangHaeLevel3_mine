package com.sparta.hh99springlv3.global.dto;

import com.sparta.hh99springlv3.global.entity.Admin;
import com.sparta.hh99springlv3.global.entity.AuthEnum;
import com.sparta.hh99springlv3.global.entity.Dept;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequestDto {
        @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
        private String email;
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
        private String password;
        private Dept dept;
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
        private String email;
        private String password;

        public Admin toEntity() {
            return Admin.builder()
                    .email(email)
                    .password(password)
                    .build();
        }
    }
}
