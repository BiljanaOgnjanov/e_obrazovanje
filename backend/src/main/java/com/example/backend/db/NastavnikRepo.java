package com.example.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Nastavnik;


@Repository
public interface NastavnikRepo extends JpaRepository<Nastavnik, String> {}


    