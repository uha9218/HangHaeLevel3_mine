package com.sparta.hh99springlv3.domain.lecture.dto;

import com.sparta.hh99springlv3.domain.lecture.entity.Category;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class LectureRequestDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateLectureRequestDto {
        @Schema(description = "강의 제목", example = "스프링 부트 입문")
        private String lectureName;
        @Schema(description = "강의 가격", example = "50000")
        private int price;
        @Schema(description = "강의 설명",example = "스프링 입문을 위한 강의입니다.")
        private String lecIntroduce;
        @Schema(description = "강의 종류",example = "SPRING")
        private Category category;
        @Schema(description = "강사 번호",example = "홍길동")
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
        @Schema(description = "강의 제목", example = "스프링 부트 입문")
        private String lectureName;
        @Schema(description = "강의 가격", example = "50000")
        private int price;
        @Schema(description = "강의 설명",example = "스프링 입문을 위한 강의입니다.")
        private String lecIntroduce;
        @Schema(description = "강사",example = "홍길동")
        private Long tutor;
        @Schema(description = "강의 종류",example = "SPRING")
        private Category category;


    }


}