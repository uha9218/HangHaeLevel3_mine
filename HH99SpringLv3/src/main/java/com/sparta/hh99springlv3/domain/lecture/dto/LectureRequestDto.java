package com.sparta.hh99springlv3.domain.lecture.dto;

import com.sparta.hh99springlv3.domain.lecture.entity.LCareer;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import lombok.*;

public class LectureRequestDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateLectureRequestDto {
        private String lectureName;
        private int price;
        private String lecIntroduce;
        private String category;
        private String tutor;

        public Lecture toEntity() {
            return Lecture.builder()
                    .lectureName(this.lectureName)
                    .price(this.price)
                    .lecIntroduce(this.lecIntroduce)
                    .tutor(this.tutor)
                    .build();
        }
    }

    @Getter
    public static class UpdateLectureRequestDto{
        private String lectureName;
        private int price;
        private String lecIntroduce;
        private String category;
        private String tutor;


    }


}