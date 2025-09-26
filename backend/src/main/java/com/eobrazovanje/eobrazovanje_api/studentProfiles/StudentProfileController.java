package com.eobrazovanje.eobrazovanje_api.studentProfiles;

import com.eobrazovanje.eobrazovanje_api.courses.dto.CourseDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.CreateStudentProfileDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.StudentProfileDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.UpdateStudentProfileDto;

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
@RequestMapping("/students")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public ResponseEntity<List<StudentProfileDto>> getAll() {
        List<StudentProfileDto> users = studentProfileService.getAll();
        
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDto> getById (@PathVariable UUID id) {
        StudentProfileDto user = studentProfileService.getById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("{id}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesForStudent(@PathVariable UUID id) {
        return ResponseEntity.ok(studentProfileService.getCoursesForStudent(id));
    }

    @PostMapping
    public ResponseEntity<StudentProfileDto> create(@Valid @RequestBody CreateStudentProfileDto data) {
        StudentProfileDto user = studentProfileService.create(data);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfileDto> update(@PathVariable UUID id, @Valid @RequestBody UpdateStudentProfileDto data) {
        StudentProfileDto user = studentProfileService.update(id, data);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentProfileService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
