package com.sparta.hh99springlv3.domain.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String username;
    private String password;
    private String email;

    private boolean manager;
    private String adminToken = "";



}
