package com.sparta.hh99springlv3.domain.admin.dto;

import com.sparta.hh99springlv3.domain.admin.entity.Admin;
import com.sparta.hh99springlv3.domain.admin.entity.AdminRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class SignupResponseDto {
        private String username;

        @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
        private String email;
        private AdminRoleEnum role;

        public SignupResponseDto(Admin admin) {
            this.username = admin.getUsername();
            this.email = admin.getEmail();
            this.role = admin.getRole();
        }
    }
}
