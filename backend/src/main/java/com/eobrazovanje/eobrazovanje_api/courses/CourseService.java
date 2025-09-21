package com.eobrazovanje.eobrazovanje_api.courses;

import com.eobrazovanje.eobrazovanje_api.courses.Course;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CreateCourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.UpdateCourseDto;
import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<CourseDto> getAll() {
        return this.repository.findAll().stream().map(this::toDto).toList();
    }

    public CourseDto getById(UUID id) {
        Course course = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        return toDto(course);
    }

    public CourseDto create(CreateCourseDto data) {
        Course course = Course.builder()
            .name(data.name())
            .year(data.year())
            .espb(data.espb())
            .build();

        return toDto(repository.save(course));
    }

    public CourseDto update(UUID id, UpdateCourseDto data) {
        Course course = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (data.name() != null) {
            course.setName(data.name());
        }

        if (data.year() != null) {
            course.setYear(data.year());
        }

        if (data.name() != null) {
            course.setName(data.name());
        }

        return toDto(repository.save(course));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found");
        }

        repository.deleteById(id);
    }

    private CourseDto toDto(Course course) {
        return new CourseDto(
            course.getId(),
            course.getName(),
            course.getYear(),
            course.getEspb(),
            course.getCreatedAt(),
            course.getUpdatedAt()
        );
    }
}
