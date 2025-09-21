package com.eobrazovanje.eobrazovanje_api.studentProfiles;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, UUID> {}
