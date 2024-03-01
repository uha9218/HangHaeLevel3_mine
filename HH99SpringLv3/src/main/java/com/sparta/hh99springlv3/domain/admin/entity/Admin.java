package com.sparta.hh99springlv3.domain.admin.entity;

import com.sparta.hh99springlv3.global.entity.Timestamped;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Admin extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AdminRoleEnum role;


    public Admin(String username, String email, String password, AdminRoleEnum role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
