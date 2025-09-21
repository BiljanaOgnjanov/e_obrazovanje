package com.eobrazovanje.eobrazovanje_api.professorProfiles.dto;

import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProfessorProfileDto(
    @NotBlank 
    String firstName,

    @NotBlank 
    String lastName,

    @Email
    String email,

    @NotBlank 
    String password,

    @NotNull
    ProfessorRole role
) {}
