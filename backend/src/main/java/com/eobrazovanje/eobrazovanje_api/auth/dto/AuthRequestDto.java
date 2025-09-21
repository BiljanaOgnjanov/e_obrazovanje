package com.eobrazovanje.eobrazovanje_api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(
    @Email
    String email,

    @NotBlank
    String password
) {}
