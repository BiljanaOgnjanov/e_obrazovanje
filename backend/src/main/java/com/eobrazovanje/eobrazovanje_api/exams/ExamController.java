package com.eobrazovanje.eobrazovanje_api.exams;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eobrazovanje.eobrazovanje_api.exams.dto.CreateExamDto;
import com.eobrazovanje.eobrazovanje_api.exams.dto.ExamDto;
import com.eobrazovanje.eobrazovanje_api.exams.dto.UpdateExamDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@Valid @RequestBody CreateExamDto data) {
        return ResponseEntity.ok(examService.createExam(data));
    }

    @PutMapping("/{examId}")
    public ResponseEntity<ExamDto> updateExam(@PathVariable UUID examId, @Valid @RequestBody UpdateExamDto data) {
        return ResponseEntity.ok(examService.updateExam(examId, data));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ExamDto>> getExamsForCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(examService.getExamsForCourse(courseId));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<ExamDto>> getExamsForProfessor(@PathVariable UUID professorId) {
        return ResponseEntity.ok(examService.getExamsForProfessor(professorId));
    }
}
