package com.eobrazovanje.eobrazovanje_api.users.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.eobrazovanje.eobrazovanje_api.users.UserType;

public record UserDto( 
    UUID id,
    String firstName,
    String lastName,
    String email,
    UserType userType,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
