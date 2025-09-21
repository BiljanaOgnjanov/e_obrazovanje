package com.eobrazovanje.eobrazovanje_api.studentProfiles.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudentProfileDto( 
    UUID id,
    String firstName,
    String lastName,
    String email,
    String studentNumber,
    String studyProgram,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
