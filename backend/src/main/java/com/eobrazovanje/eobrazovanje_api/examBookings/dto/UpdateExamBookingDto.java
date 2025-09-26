package com.eobrazovanje.eobrazovanje_api.examBookings.dto;

import com.eobrazovanje.eobrazovanje_api.examBookings.ExamBookingStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UpdateExamBookingDto(
    ExamBookingStatus status,

    @Min(0) @Max(50)
    Integer test1,

    @Min(0) @Max(50)
    Integer test2,

    @Min(0) @Max(5)
    Integer attendance
) {}
