package com.sparta.hh99springlv3.domain.tutor.entity;

import com.sparta.hh99springlv3.domain.tutor.dto.TutorRequestDto.UpdateTutorRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tutorName;
    private String career;
    private String company;
    @Column(nullable = false)
    private String phoneNumber;
    private String tuIntroduce;

    public void updateTutorInfo(UpdateTutorRequestDto tutor){
        this.career = tutor.getCareer();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.tuIntroduce = tutor.getTuIntroduce();
    }
}
