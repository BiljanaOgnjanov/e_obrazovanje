package com.example.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.models.Uplate;
import java.util.List;


@Repository
public interface UplateRepo extends JpaRepository<Uplate, Integer> {
    List<Uplate> findByKorisnickoIme(String korisnickoIme);   
}