package com.eobrazovanje.eobrazovanje_api.adminProfiles.dto;

import com.eobrazovanje.eobrazovanje_api.adminProfiles.AdminRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAdminProfileDto(
    @NotBlank 
    String firstName,

    @NotBlank 
    String lastName,

    @Email
    String email,

    @NotBlank 
    String password,

    @NotNull
    AdminRole role
) {}
