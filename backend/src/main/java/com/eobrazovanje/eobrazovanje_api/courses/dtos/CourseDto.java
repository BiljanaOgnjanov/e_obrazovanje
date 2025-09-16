package com.eobrazovanje.eobrazovanje_api.courses.dtos;

import java.util.UUID;

public record CourseDto(
    UUID id,
    String name,
    int year,
    int espb
) {}
