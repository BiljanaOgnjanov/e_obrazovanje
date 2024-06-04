package com.example.backend.db;

import com.example.backend.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepo extends JpaRepository<Korisnik, String> {
    Korisnik findByUsernameAndPassword(String username, String password);
}