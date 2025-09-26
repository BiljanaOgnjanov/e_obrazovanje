package com.eobrazovanje.eobrazovanje_api.examBookings.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateExamBookingDto(
    @NotNull UUID examId,
    @NotNull UUID studentId
) {}
