package com.sparta.hh99springlv3.domain.admin.dto;

import com.sparta.hh99springlv3.domain.admin.entity.Admin;
import com.sparta.hh99springlv3.domain.admin.entity.AuthEnum;
import com.sparta.hh99springlv3.domain.admin.entity.Dept;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class SignupResponseDto {
            private Long AdminId;
            private String email;
            private String password;
            private Dept dept;
            private AuthEnum authority;

            public SignupResponseDto(Admin admin){
                this.AdminId = admin.getAdminId();
                this.email = admin.getEmail();
                this.password = admin.getPassword();
                this.dept = admin.getDept();
                this.authority = admin.getAuthority();

            }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class LoginResponseDto{
        private String email;
        private String password;
        public LoginResponseDto(Admin admin){
            this.email =admin.getEmail();
            this.password= admin.getPassword();
        }
    }
}
