package com.sparta.hh99springlv3.domain.lecture.dto;

import com.sparta.hh99springlv3.domain.lecture.entity.Category;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class LectureRequestDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateLectureRequestDto {
        private String lectureName;
        private int price;
        private String lecIntroduce;
        private Category category;
        private Long tutor;
        private LocalDateTime createdAt;

        public Lecture toEntity() {
            return Lecture.builder()
                    .lectureName(this.lectureName)
                    .price(this.price)
                    .lecIntroduce(this.lecIntroduce)
                    .category(this.category)
                    .tutor(this.tutor)
                    .createdAt(this.createdAt)
                    .category(this.category)
                    .build();
        }
    }

    @Getter
    public static class UpdateLectureRequestDto {
        private String lectureName;
        private int price;
        private String lecIntroduce;
        private Long tutor;
        private Category category;


    }


}