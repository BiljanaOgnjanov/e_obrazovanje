package com.eobrazovanje.eobrazovanje_api.users.dto;

import com.eobrazovanje.eobrazovanje_api.users.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
    @NotBlank 
    String firstName,

    @NotBlank 
    String lastName,

    @Email
    @NotBlank
    String email,

    @NotBlank 
    String password,

    @NotNull
    UserType userType
) {}
