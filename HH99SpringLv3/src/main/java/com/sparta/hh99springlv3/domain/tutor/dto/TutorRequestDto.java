package com.sparta.hh99springlv3.domain.tutor.dto;

import com.sparta.hh99springlv3.domain.tutor.entity.Tutor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class TutorRequestDto {
    @Getter
    public static class CreateTutorRequestDto {
        @Schema(description = "강사 이름", example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        private String tutorName;

        @Schema(description = "강사 경력", example = "20")
        private String career;

        @Schema(description = "소속 회사", example = "금성 전자")
        private String company;

        @Schema(description = "전화번호", example = "010-1234-5678")
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phoneNumber;

        @Schema(description = "강사 소개", example = "세상을 널리 이롭게 하는 강사")
        private String tuIntroduce;

        public Tutor toEntity() {
            return Tutor.builder()
                    .tutorName(this.tutorName)
                    .career(this.career)
                    .company(this.company)
                    .phoneNumber(this.phoneNumber)
                    .tuIntroduce(this.tuIntroduce)
                    .build();
        }
    }

    @Getter
    public static class UpdateTutorRequestDto {
        @Schema(description = "강사 경력", example = "20")
        private String career;

        @Schema(description = "소속 회사", example = "금성 전자")
        private String company;

        @Schema(description = "전화번호", example = "010-1234-5678")
        private String phoneNumber;

        @Schema(description = "강사 소개", example = "세상을 널리 이롭게 하는 강사")
        private String tuIntroduce;
    }

}
