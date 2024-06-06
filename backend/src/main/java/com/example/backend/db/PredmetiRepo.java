package com.example.backend.db;

import java.util.List;
import com.example.backend.models.MojiPredmetiDTO;
import com.example.backend.models.PracenjePredmeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PredmetiRepo extends JpaRepository<PracenjePredmeta, Integer> {

    @Query("SELECT new com.example.demo.dto.MojiPredmetiDTO(p.idPredmeta, p.imePredmeta, p.godina, p.brojKredita, p.opisPredmeta, pp.idPracenjaPredmeta, pp.skolskaGodina, pp.polozenIspit, pp.ocena) " +
        "FROM PracenjePredmeta pp JOIN pp.predmet p WHERE pp.korisnickoIme = :username")
    List<MojiPredmetiDTO> findPredmetiByKorisnickoIme(@Param("username") String username);
}
