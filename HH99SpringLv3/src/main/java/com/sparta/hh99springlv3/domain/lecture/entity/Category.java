package com.sparta.hh99springlv3.domain.lecture.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    SPRING("SPRING"), REACT("REACT"), NODE("NODE");
    private final String category;
}
