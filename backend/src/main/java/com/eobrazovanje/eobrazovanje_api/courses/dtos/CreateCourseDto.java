package com.eobrazovanje.eobrazovanje_api.courses.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourseDto(
    @NotBlank 
    String name,

    @NotNull
    @Min(1)
    @Max(4) 
    Integer year,

    @NotNull
    @Min(1)
    Integer espb
) {}
