package com.sparta.hh99springlv3.domain.admin.entity;

import com.sparta.hh99springlv3.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Admin extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String authority;
    @Enumerated(value = EnumType.STRING)
    private AdminRoleEnum role;


    public Admin(String username, String email, String password, String authority, AdminRoleEnum role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.role = role;
    }
}
