package com.eobrazovanje.eobrazovanje_api.exams.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateExamDto(
    @NotNull
    UUID courseId,

    @NotNull
    UUID professorId,

    @Min(1)
    @Max(12)
    int examMonth,

    @NotNull
    LocalDateTime examDate
) {}
