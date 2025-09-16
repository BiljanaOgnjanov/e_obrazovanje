package com.eobrazovanje.eobrazovanje_api.courses.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record CreateCourseDto(
    @NotBlank 
    String name,

    @Min(1)
    @Max(4) 
    int year,

    @Min(1)
    int espb
) {}
