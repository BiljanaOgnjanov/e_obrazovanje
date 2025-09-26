package com.eobrazovanje.eobrazovanje_api.exams;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, UUID> {
    List<Exam> findByCourseId(UUID courseId);

    List<Exam> findByProfessorId(UUID professorId);

    List<Exam> findByStatus(ExamStatus status);
}
