package com.sparta.hh99springlv3.domain.lecture.repository;

import com.sparta.hh99springlv3.domain.lecture.entity.Category;
import com.sparta.hh99springlv3.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByCategory(Category category);

    List<Lecture> findAllByTutorOrderByCreatedAtDesc(long tutorId);
}
