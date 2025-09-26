package com.eobrazovanje.eobrazovanje_api.courses;

import com.eobrazovanje.eobrazovanje_api.courses.Course;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CreateCourseDto;
import com.eobrazovanje.eobrazovanje_api.courses.dto.UpdateCourseDto;
import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorProfile;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorProfileRepository;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.ProfessorProfileDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.StudentProfile;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.StudentProfileRepository;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.StudentProfileDto;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final ProfessorProfileRepository professorProfileRepository;

    public CourseService(
        CourseRepository courseRepository,
        StudentProfileRepository studentProfileRepository,
        ProfessorProfileRepository professorProfileRepository
    ) {
        this.courseRepository = courseRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.professorProfileRepository = professorProfileRepository;
    }

    public List<CourseDto> getAll() {
        return this.courseRepository.findAll().stream().map(this::toCourseDto).toList();
    }

    public CourseDto getById(UUID id) {
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        return toCourseDto(course);
    }

    public CourseDto create(CreateCourseDto data) {
        Course course = Course.builder()
            .name(data.name())
            .year(data.year())
            .espb(data.espb())
            .build();

        return toCourseDto(courseRepository.save(course));
    }

    public CourseDto update(UUID id, UpdateCourseDto data) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (data.name() != null) {
            course.setName(data.name());
        }

        if (data.year() != null) {
            course.setYear(data.year());
        }

        if (data.espb() != null) {
            course.setEspb(data.espb());
        }

        return toCourseDto(courseRepository.save(course));
    }

    public void delete(UUID id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found");
        }

        courseRepository.deleteById(id);
    }

    public void assignStudent(UUID studentId, UUID courseId) {
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        student.getCourses().add(course);
        studentProfileRepository.save(student);
    }

    public void assignProfessor(UUID professorId, UUID courseId) {
        ProfessorProfile prof = professorProfileRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        prof.getCourses().add(course);
        professorProfileRepository.save(prof);
    }

    public void removeStudent(UUID studentId, UUID courseId) {
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        student.getCourses().remove(course);
        studentProfileRepository.save(student);
    }

    public void removeProfessor(UUID professorId, UUID courseId) {
        ProfessorProfile prof = professorProfileRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        prof.getCourses().remove(course);
        professorProfileRepository.save(prof);
    }

    public List<StudentProfileDto> getStudentsForCourse(UUID courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        return course.getStudents()
            .stream()
            .map(this::toStudentProfileDto)
            .toList();
    }

    public List<ProfessorProfileDto> getProfessorsForCourse(UUID courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        return course.getProfessors()
            .stream()
            .map(this::toProfessorProfileDto)
            .toList();
    }

    private CourseDto toCourseDto(Course course) {
        return new CourseDto(
            course.getId(),
            course.getName(),
            course.getYear(),
            course.getEspb(),
            course.getCreatedAt(),
            course.getUpdatedAt()
        );
    }

    private ProfessorProfileDto toProfessorProfileDto(ProfessorProfile user) {
        return new ProfessorProfileDto(
            user.getUser().getId(),
            user.getUser().getFirstName(),
            user.getUser().getLastName(),
            user.getUser().getEmail(),
            user.getRole(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

    private StudentProfileDto toStudentProfileDto(StudentProfile user) {
        return new StudentProfileDto(
            user.getUser().getId(),
            user.getUser().getFirstName(),
            user.getUser().getLastName(),
            user.getUser().getEmail(),
            user.getStudentNumber(),
            user.getStudyProgram(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
