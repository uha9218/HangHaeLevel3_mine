package com.sparta.hh99springlv3.domain.lecture.repository;

import com.sparta.hh99springlv3.domain.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
}
