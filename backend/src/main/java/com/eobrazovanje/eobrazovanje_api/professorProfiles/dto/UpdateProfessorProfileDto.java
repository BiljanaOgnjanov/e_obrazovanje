package com.eobrazovanje.eobrazovanje_api.professorProfiles.dto;

import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorRole;

public record UpdateProfessorProfileDto( 
    String firstName,
    String lastName,
    String password,
    ProfessorRole role
) {}
