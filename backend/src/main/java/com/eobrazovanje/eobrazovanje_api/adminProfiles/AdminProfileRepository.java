package com.eobrazovanje.eobrazovanje_api.adminProfiles;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProfileRepository extends JpaRepository<AdminProfile, UUID> {}
