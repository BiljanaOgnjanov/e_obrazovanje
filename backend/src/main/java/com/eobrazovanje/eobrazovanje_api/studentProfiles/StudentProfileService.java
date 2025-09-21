package com.eobrazovanje.eobrazovanje_api.studentProfiles;

import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.CreateStudentProfileDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.StudentProfileDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.dto.UpdateStudentProfileDto;
import com.eobrazovanje.eobrazovanje_api.users.User;
import com.eobrazovanje.eobrazovanje_api.users.UserRepository;
import com.eobrazovanje.eobrazovanje_api.users.UserType;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public StudentProfileService(
        StudentProfileRepository studentProfileRepository,
        UserRepository userRepository,
        PasswordEncoder encoder
    ) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<StudentProfileDto> getAll() {
        return this.studentProfileRepository.findAll().stream().map(this::toDto).toList();
    }

    public StudentProfileDto getById(UUID id) {
        StudentProfile user = this.studentProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));

        return toDto(user);
    }

    public StudentProfileDto create(CreateStudentProfileDto data) {
        User user = User.builder()
            .firstName(data.firstName())
            .lastName(data.lastName())
            .email(data.email())
            .password(encoder.encode(data.password()))
            .userType(UserType.STUDENT)
            .build();
        
        userRepository.save(user);

        StudentProfile profile = StudentProfile.builder()
            .user(user)
            .studentNumber(data.studentNumber())
            .studyProgram(data.studyProgram())
            .build();

        studentProfileRepository.save(profile);

        return toDto(profile);
    }

    public StudentProfileDto update(UUID id, UpdateStudentProfileDto data) {
        StudentProfile profile = studentProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));

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

        if (data.studentNumber() != null) {
            profile.setStudentNumber(data.studentNumber());
        }

        if (data.studyProgram() != null) {
            profile.setStudyProgram(data.studyProgram());
        }

        userRepository.save(user);

        studentProfileRepository.save(profile);

        return toDto(profile);
    }

    public void delete(UUID id) {
        if (!studentProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student profile not found");
        }

        studentProfileRepository.deleteById(id);
    }

    private StudentProfileDto toDto(StudentProfile user) {
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
