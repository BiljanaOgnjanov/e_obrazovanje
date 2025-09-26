package com.eobrazovanje.eobrazovanje_api.exams.dto;

import java.time.LocalDateTime;

import com.eobrazovanje.eobrazovanje_api.exams.ExamStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UpdateExamDto(
    @Min(1)
    @Max(12)
    Integer examMonth,
    LocalDateTime examDate,
    ExamStatus status
) {}
