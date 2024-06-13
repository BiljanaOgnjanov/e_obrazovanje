package com.example.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.models.Predmet;

public interface PredmetiAPRepo extends JpaRepository<Predmet, Integer> {
}