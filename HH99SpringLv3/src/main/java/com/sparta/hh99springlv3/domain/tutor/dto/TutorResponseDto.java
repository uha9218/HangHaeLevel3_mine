package com.sparta.hh99springlv3.domain.tutor.dto;

import com.sparta.hh99springlv3.domain.tutor.entity.Tutor;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class TutorResponseDto {
    @Getter
    public static class CreateTutorResponseDto{
        private String tutorName;
        private String career;
        private String company;
        private String phoneNumber;
        private String tuIntroduce;

        public CreateTutorResponseDto(Tutor tutor){
            this.tutorName = tutor.getTutorName();
            this.career = tutor.getCareer();
            this.company = tutor.getCompany();
            this.phoneNumber = tutor.getPhoneNumber();
            this.tuIntroduce = tutor.getTuIntroduce();
        }
    }
    @Getter
    public static class ReadTutorResponseDto{
        private String tutorName;
        private String career;
        private String company;
        private String phoneNumber;
        private String tuIntroduce;

        public ReadTutorResponseDto(Tutor tutor){
            this.tutorName = tutor.getTutorName();
            this.career = tutor.getCareer();
            this.company = tutor.getCompany();
            this.phoneNumber = tutor.getPhoneNumber();
            this.tuIntroduce = tutor.getTuIntroduce();
        }

    }
}
