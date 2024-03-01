package com.sparta.hh99springlv3.domain.tutor.dto;

import com.sparta.hh99springlv3.domain.tutor.entity.Tutor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class TutorRequestDto {
    @Getter
    public static class CreateTutorRequestDto{
        @NotBlank(message = "이름을 입력해주세요.")
        private String tutorName;
        private String career;
        private String company;
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phoneNumber;
        private String tuIntroduce;

        public Tutor toEntity(){
            return Tutor.builder()
                    .tutorName(this.tutorName)
                    .career(this.career)
                    .company(this.company)
                    .phoneNumber(this.phoneNumber)
                    .tuIntroduce(this.tuIntroduce)
                    .build();
        }
    }

}
