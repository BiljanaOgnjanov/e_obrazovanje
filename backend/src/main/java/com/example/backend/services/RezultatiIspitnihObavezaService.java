package com.example.backend.services;

import com.example.backend.models.IspitniRezultatDTO;
import com.example.backend.db.RezultatiIspitnihObavezaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezultatiIspitnihObavezaService {
   
    @Autowired
    private RezultatiIspitnihObavezaRepo rezultatiIspitnihObavezaRepo;

    public List<IspitniRezultatDTO> getIspitniRezultatiByPracenjePredmetaId(int idPracenjaPredmeta) {
        return rezultatiIspitnihObavezaRepo.findIspitniRezultatiByPracenjePredmetaId(idPracenjaPredmeta);
    }
}
