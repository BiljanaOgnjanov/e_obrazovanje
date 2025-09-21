package com.eobrazovanje.eobrazovanje_api.courses.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CourseDto(
    UUID id,
    String name,
    int year,
    int espb,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
