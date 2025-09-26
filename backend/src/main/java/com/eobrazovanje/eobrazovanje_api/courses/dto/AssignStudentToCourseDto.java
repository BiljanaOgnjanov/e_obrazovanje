package com.eobrazovanje.eobrazovanje_api.courses.dto;

import java.util.UUID;

public record AssignStudentToCourseDto(
    UUID studentId,
    UUID courseId
) {}
