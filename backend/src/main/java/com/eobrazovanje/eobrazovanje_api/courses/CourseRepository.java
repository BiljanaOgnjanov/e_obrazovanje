package com.eobrazovanje.eobrazovanje_api.courses;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, UUID> {}
