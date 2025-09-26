package com.eobrazovanje.eobrazovanje_api.examBookings.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record GradeDto(
    @Min(0)
    @Max(50)
    int test1,

    @Min(0)
    @Max(50)
    int test2,

    @Min(0)
    @Max(5)
    int attendance
) {}
