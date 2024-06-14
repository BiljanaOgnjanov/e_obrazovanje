package com.example.backend.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.models.Nastavnik;
import com.example.backend.models.PredavanjePredmeta;
import com.example.backend.models.Predmet;

public interface PredavanjePredmetaRepo extends JpaRepository<PredavanjePredmeta, Integer> {
    
    @Query("SELECT p FROM PredavanjePredmeta pp JOIN pp.predmet p WHERE pp.korisnickoIme = :username")
    List<Predmet> findCoursesByProfessorUsername(@Param("username") String username);

    @Query("SELECT n FROM Nastavnik n WHERE n.username NOT IN (SELECT pp.korisnickoIme FROM PredavanjePredmeta pp WHERE pp.predmet.idPredmeta = :idPredmeta)")
    List<Nastavnik> findProfessorsNotTeachingCourse(@Param("idPredmeta") int idPredmeta);

    @Query("SELECT n FROM Nastavnik n WHERE n.username IN (SELECT pp.korisnickoIme FROM PredavanjePredmeta pp WHERE pp.predmet.idPredmeta = :idPredmeta)")
    List<Nastavnik> findProfessorsTeachingCourse(@Param("idPredmeta") int idPredmeta);

}