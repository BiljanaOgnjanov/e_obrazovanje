package com.eobrazovanje.eobrazovanje_api.courses.dto;

import java.util.UUID;

public record AssignProfessorToCourseDto(
    UUID professorId,
    UUID courseId
) {}
