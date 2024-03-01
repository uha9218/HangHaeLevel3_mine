package com.sparta.hh99springlv3.domain.lecture.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.hh99springlv3.domain.lecture.entity.LCareer;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class LectureResponseDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateLectureResponseDto{
            private String lectureName;
            private int price;
            private String lecIntroduce;
            private LCareer category;
            private String tutor;

            public Lecture toEntity(){
                return Lecture.builder()
                        .lectureName(this.lectureName)
                        .price(this.price)
                        .lecIntroduce(this.lecIntroduce)
                        .category(this.category)
                        .tutor(this.tutor)
                        .build();
            }
    }

    public static class GetLectureResponseDto{
        private Long id;
        private String lectureName;
        private int price;
        private String lecIntroduce;
        private LCareer category;
        private String tutor;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;

        public GetLectureResponseDto(Lecture lecture) {
            this.id = lecture.getId();
            this.lectureName = lecture.getLectureName();
            this.price = lecture.getPrice();
            this.lecIntroduce = lecture.getLecIntroduce();
            this.category = lecture.getCategory();
            this.tutor = lecture.getTutor();
        }
    }
}
