package com.example.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Administrator;

@Repository
public interface AdministratorRepo extends JpaRepository<Administrator, String> {
    
}
