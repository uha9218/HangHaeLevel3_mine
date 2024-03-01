package com.sparta.hh99springlv3.domain.admin.dto;

import com.sparta.hh99springlv3.domain.admin.entity.Admin;
import com.sparta.hh99springlv3.domain.admin.entity.AdminRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor@NoArgsConstructor
    public static class SignupRequestDto{
        private String username;
        private String password;

        @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
        private String email;

        private boolean manager;
        private String adminToken = "";

        public Admin toEntity() {
            return Admin.builder()
                    .username(this.username)
                    .password(this.password)
                    .email(this.email)
                    .role(this.manager ? AdminRoleEnum.MANAGER : AdminRoleEnum.ADMIN)
                    .build();
        }
    }
}
