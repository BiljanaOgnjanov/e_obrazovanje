package com.eobrazovanje.eobrazovanje_api.professorProfiles;

import com.eobrazovanje.eobrazovanje_api.courses.Course;
import com.eobrazovanje.eobrazovanje_api.courses.dto.CourseDto;
import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.CreateProfessorProfileDto;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.ProfessorProfileDto;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.UpdateProfessorProfileDto;
import com.eobrazovanje.eobrazovanje_api.users.User;
import com.eobrazovanje.eobrazovanje_api.users.UserRepository;
import com.eobrazovanje.eobrazovanje_api.users.UserType;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfessorProfileService {
    private final ProfessorProfileRepository professorProfileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ProfessorProfileService(
        ProfessorProfileRepository professorProfileRepository,
        UserRepository userRepository,
        PasswordEncoder encoder
    ) {
        this.professorProfileRepository = professorProfileRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<ProfessorProfileDto> getAll() {
        return this.professorProfileRepository.findAll().stream().map(this::toDto).toList();
    }

    public ProfessorProfileDto getById(UUID id) {
        ProfessorProfile user = this.professorProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor profile not found"));

        return toDto(user);
    }

    public List<CourseDto> getCoursesForProfessor(UUID professorId) {
        ProfessorProfile prof = professorProfileRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found."));

        return prof.getCourses()
            .stream()
            .map(this::toCourseDto)
            .toList();
    }

    public ProfessorProfileDto create(CreateProfessorProfileDto data) {
        User user = User.builder()
            .firstName(data.firstName())
            .lastName(data.lastName())
            .email(data.email())
            .password(encoder.encode(data.password()))
            .userType(UserType.PROFESSOR)
            .build();
        
        userRepository.save(user);

        ProfessorProfile profile = ProfessorProfile.builder()
            .user(user)
            .role(data.role())
            .build();

        professorProfileRepository.save(profile);

        return toDto(profile);
    }

    public ProfessorProfileDto update(UUID id, UpdateProfessorProfileDto data) {
        ProfessorProfile profile = professorProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor profile not found"));

        User user = profile.getUser();

        if (data.firstName() != null) {
            user.setFirstName(data.firstName());
        }

        if (data.lastName() != null) {
            user.setLastName(data.lastName());
        }

        if (data.password() != null) {
            user.setPassword(data.password());
        }

        if (data.role() != null) {
            profile.setRole(data.role());
        }

        userRepository.save(user);

        professorProfileRepository.save(profile);

        return toDto(profile);
    }

    public void delete(UUID id) {
        if (!professorProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor profile not found");
        }

        professorProfileRepository.deleteById(id);
    }

    private ProfessorProfileDto toDto(ProfessorProfile user) {
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
}
