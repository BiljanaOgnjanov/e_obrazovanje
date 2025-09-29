package com.eobrazovanje.eobrazovanje_api.exams;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.eobrazovanje.eobrazovanje_api.courses.Course;
import com.eobrazovanje.eobrazovanje_api.courses.CourseRepository;
import com.eobrazovanje.eobrazovanje_api.exams.dto.CreateExamDto;
import com.eobrazovanje.eobrazovanje_api.exams.dto.ExamDto;
import com.eobrazovanje.eobrazovanje_api.exams.dto.UpdateExamDto;
import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorProfile;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorProfileRepository;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final ProfessorProfileRepository professorRepository;

    public ExamService(
        ExamRepository examRepository,
        CourseRepository courseRepository,
        ProfessorProfileRepository professorRepository
    ) {
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    public ExamDto createExam(CreateExamDto data) {
        Course course = courseRepository.findById(data.courseId())
            .orElseThrow(() -> new ResourceNotFoundException("Course not found."));
        
        ProfessorProfile professor = professorRepository.findById(data.professorId())
            .orElseThrow(() -> new ResourceNotFoundException("Professor not found."));

        Exam exam = Exam.builder()
            .course(course)
            .professor(professor)
            .examMonth(data.examMonth())
            .examDate(data.examDate())
            .status(ExamStatus.SCHEDULED)
            .build();

        return toDto(examRepository.save(exam));
    }

    public ExamDto updateExam(UUID examId, UpdateExamDto data) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam not found."));

        if (data.examMonth() != null) {
            exam.setExamMonth(data.examMonth());
        }

        if (data.examDate() != null) {
            exam.setExamDate(data.examDate());
        }

        if (data.status() != null) {
            exam.setStatus(data.status());
        }

        return toDto(examRepository.save(exam));
    }

    public List<ExamDto> getExamsForCourse(UUID courseId) {
        return examRepository.findByCourseId(courseId)
            .stream()
            .map(this::toDto)
            .toList();
    }

    public List<ExamDto> getExamsForProfessor(UUID professorId) {
        return examRepository.findByProfessorId(professorId)
            .stream()
            .map(this::toDto)
            .toList();
    }

    public ExamDto getExamById(UUID examId) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam not found."));

        return toDto(examRepository.save(exam));
    }

    private ExamDto toDto(Exam exam) {
        return new ExamDto(
            exam.getId(),
            exam.getCourse().getId(),
            exam.getProfessor().getId(),
            exam.getExamMonth(),
            exam.getExamDate(),
            exam.getStatus(),
            exam.getCreatedAt(),
            exam.getUpdatedAt()
        );
    }
}
