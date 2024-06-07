package com.example.backend.db;

import com.example.backend.models.IspitniRezultatDTO;
import com.example.backend.models.RezultatiIspitnihObaveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RezultatiIspitnihObavezaRepo extends JpaRepository<RezultatiIspitnihObaveza, Integer> {

    @Query("SELECT new com.example.backend.models.IspitniRezultatDTO(io.nazivIspitneObaveze, io.brojPoena, rio.osvojenoPoena, rio.rok, rio.polozenIspitURoku) " +
    "FROM RezultatiIspitnihObaveza rio " +
    "JOIN rio.ispitneObaveze io " +
    "WHERE rio.pracenjePredmeta.idPracenjaPredmeta = :idPracenjaPredmeta")
    List<IspitniRezultatDTO> findIspitniRezultatiByPracenjePredmetaId(@Param("idPracenjaPredmeta") int idPracenjaPredmeta);

    
}
