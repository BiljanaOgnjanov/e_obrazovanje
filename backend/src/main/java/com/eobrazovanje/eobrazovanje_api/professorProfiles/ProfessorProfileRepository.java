package com.eobrazovanje.eobrazovanje_api.professorProfiles;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorProfileRepository extends JpaRepository<ProfessorProfile, UUID> {}
