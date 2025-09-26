package com.eobrazovanje.eobrazovanje_api.exams.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.eobrazovanje.eobrazovanje_api.exams.ExamStatus;

public record ExamDto(
    UUID id,
    UUID courseId,
    UUID professorId,
    int examMonth,
    LocalDateTime examDate,
    ExamStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
