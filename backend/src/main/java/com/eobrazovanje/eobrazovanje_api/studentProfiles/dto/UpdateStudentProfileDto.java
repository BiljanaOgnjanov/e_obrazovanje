package com.eobrazovanje.eobrazovanje_api.studentProfiles.dto;

public record UpdateStudentProfileDto( 
    String firstName,
    String lastName,
    String password,
    String studentNumber,
    String studyProgram
) {}
