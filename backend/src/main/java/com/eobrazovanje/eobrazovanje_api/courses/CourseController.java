package com.eobrazovanje.eobrazovanje_api.courses;

import com.eobrazovanje.eobrazovanje_api.courses.CourseService;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CreateCourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.UpdateCourseDto;

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
}
