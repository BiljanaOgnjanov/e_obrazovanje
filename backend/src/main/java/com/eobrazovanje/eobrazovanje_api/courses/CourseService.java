package com.eobrazovanje.eobrazovanje_api.courses;

import com.eobrazovanje.eobrazovanje_api.courses.dtos.CourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dtos.CreateCourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dtos.UpdateCourseDto;

import java.util.List;
import java.util.UUID;

public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<CourseDto> getAll() {
        return this.repository.findAll().stream().map(this::toDto).toList();
    }

    public CourseDto getById(UUID id) {
        Course course = this.repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        return toDto(course);
    }

    private CourseDto toDto(Course course) {
        return new CourseDto(
            course.getId(),
            course.getName(),
            course.getYear(),
            course.getEspb()
        );
    }
}
