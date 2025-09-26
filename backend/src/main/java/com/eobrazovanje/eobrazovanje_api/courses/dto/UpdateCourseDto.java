package com.eobrazovanje.eobrazovanje_api.courses.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UpdateCourseDto(
    String name,

    @Min(1)
    @Max(4)
    Integer year,

    @Min(1)
    Integer espb
) {}
