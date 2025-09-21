package com.eobrazovanje.eobrazovanje_api.adminProfiles.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.eobrazovanje.eobrazovanje_api.adminProfiles.AdminRole;

public record AdminProfileDto( 
    UUID id,
    String firstName,
    String lastName,
    String email,
    AdminRole role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
