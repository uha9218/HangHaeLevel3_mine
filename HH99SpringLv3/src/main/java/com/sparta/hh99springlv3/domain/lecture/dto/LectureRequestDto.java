package com.sparta.hh99springlv3.domain.lecture.dto;

import com.sparta.hh99springlv3.domain.lecture.entity.Category;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LectureRequestDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateLectureRequestDto {
        private String lectureName;
        private int price;
        private String lecIntroduce;
        private Category category;
        private String tutor;

        public Lecture toEntity() {
            return Lecture.builder()
                    .lectureName(this.lectureName)
                    .price(this.price)
                    .lecIntroduce(this.lecIntroduce)
                    .category(this.category)
                    .tutor(this.tutor)
                    .build();
        }
    }

    @Getter
    public static class UpdateLectureRequestDto{
        private String lectureName;
        private int price;
        private String lecIntroduce;
        private Category category;
        private String tutor;


    }


}