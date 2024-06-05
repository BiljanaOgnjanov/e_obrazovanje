package com.example.backend.db;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Student;



@Repository
public interface StudentRepo extends JpaRepository<Student, String> {}

