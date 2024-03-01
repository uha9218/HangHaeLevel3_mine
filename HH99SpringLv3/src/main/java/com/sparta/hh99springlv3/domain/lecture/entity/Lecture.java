package com.sparta.hh99springlv3.domain.lecture.entity;

import com.sparta.hh99springlv3.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Lecture extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String lectureName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private String lecIntroduce;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LCareer category;

    @Column(nullable = false)
    private String tutor;



}
