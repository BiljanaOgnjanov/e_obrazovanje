package com.eobrazovanje.eobrazovanje_api.auth.dto;

import java.util.UUID;

public record AuthResponseDto(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String userType
) {}
