package com.example.backend.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.models.Placanja;
import java.util.List;


@Repository
public interface PlacanjaRepo extends JpaRepository<Placanja, Integer> {
    List<Placanja> findByKorisnickoIme(String korisnickoIme);   
}