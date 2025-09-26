package com.eobrazovanje.eobrazovanje_api.courses;

import com.eobrazovanje.eobrazovanje_api.courses.CourseService;
import com.eobrazovanje.eobrazovanje_api.courses.dto.AssignProfessorToCourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.AssignStudentToCourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CreateCourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.UpdateCourseDto;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.ProfessorProfileDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.StudentProfileDto;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAll();
        
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById (@PathVariable UUID id) {
        CourseDto course = courseService.getById(id);

        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CreateCourseDto data) {
        CourseDto course = courseService.create(data);

        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable UUID id, @Valid @RequestBody UpdateCourseDto data) {
        CourseDto course = courseService.update(id, data);

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-student")
    public ResponseEntity<Void> assignStudent(@RequestBody @Valid AssignStudentToCourseDto data) {
        courseService.assignStudent(data.studentId(), data.courseId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-professor")
    public ResponseEntity<Void> assignProfessor(@RequestBody @Valid AssignProfessorToCourseDto data) {
        courseService.assignProfessor(data.professorId(), data.courseId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Void> removeStudent(
            @PathVariable UUID courseId,
            @PathVariable UUID studentId) {
        courseService.removeStudent(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseId}/professors/{professorId}")
    public ResponseEntity<Void> removeProfessor(
            @PathVariable UUID courseId,
            @PathVariable UUID professorId) {
        courseService.removeProfessor(professorId, courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentProfileDto>> getStudents(@PathVariable UUID courseId) {
        return ResponseEntity.ok(courseService.getStudentsForCourse(courseId));
    }

    @GetMapping("/{courseId}/professors")
    public ResponseEntity<List<ProfessorProfileDto>> getProfessors(@PathVariable UUID courseId) {
        return ResponseEntity.ok(courseService.getProfessorsForCourse(courseId));
    }
}
