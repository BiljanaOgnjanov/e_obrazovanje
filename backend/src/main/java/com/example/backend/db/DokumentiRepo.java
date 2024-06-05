package com.example.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Dokument;
import java.util.List;

@Repository
public interface DokumentiRepo extends JpaRepository<Dokument, Integer> {
    List<Dokument> findByKorisnickoIme(String korisnickoIme);
}
