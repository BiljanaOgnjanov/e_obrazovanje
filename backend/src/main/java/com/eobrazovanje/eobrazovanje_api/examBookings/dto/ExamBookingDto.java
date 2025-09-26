package com.eobrazovanje.eobrazovanje_api.examBookings.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.eobrazovanje.eobrazovanje_api.examBookings.ExamBookingStatus;

public record ExamBookingDto(
    UUID id,
    UUID examId,
    UUID studentId,
    ExamBookingStatus status,
    Integer test1,
    Integer test2,
    Integer attendance,
    Integer totalScore,
    Integer grade,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
