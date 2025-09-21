package com.eobrazovanje.eobrazovanje_api.studentProfiles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateStudentProfileDto(
    @NotBlank 
    String firstName,

    @NotBlank 
    String lastName,

    @Email
    String email,

    @NotBlank 
    String password,

    @NotBlank
    String studentNumber,

    @NotBlank
    String studyProgram
) {}
