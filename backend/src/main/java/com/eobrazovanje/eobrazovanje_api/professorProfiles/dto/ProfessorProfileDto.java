package com.eobrazovanje.eobrazovanje_api.professorProfiles.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorRole;

public record ProfessorProfileDto( 
    UUID id,
    String firstName,
    String lastName,
    String email,
    ProfessorRole role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
