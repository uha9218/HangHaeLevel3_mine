package com.sparta.hh99springlv3.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Dept {
    CURRICULUM("커리큘럼"), MARKETING("마케팅"), DEVELOPER("개발");
    private final String dept;
}
