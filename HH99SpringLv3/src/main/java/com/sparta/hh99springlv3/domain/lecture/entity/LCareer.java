package com.sparta.hh99springlv3.domain.lecture.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LCareer {
    SPRING("Spring"),REACT("React"),NODE("Node");
    private final String value;
}
