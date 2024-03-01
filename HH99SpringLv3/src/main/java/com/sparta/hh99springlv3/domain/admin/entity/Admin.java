package com.sparta.hh99springlv3.domain.admin.entity;

import com.sparta.hh99springlv3.domain.admin.dto.AdminRequestDto;
import com.sparta.hh99springlv3.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="admins")
public class Admin extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AdminId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Dept dept;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AuthEnum Authority;

}
