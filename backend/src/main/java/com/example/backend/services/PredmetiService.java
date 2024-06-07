package com.example.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.backend.db.PredmetiRepo;
import com.example.backend.models.MojiPredmetiDTO;
import org.springframework.stereotype.Service;

@Service
public class PredmetiService {

    @Autowired
    private PredmetiRepo predmetiRepository;

    
    public List<MojiPredmetiDTO> getPredmetiByKorisnickoIme(String korisnickoIme) {
        return predmetiRepository.findPredmetiByKorisnickoIme(korisnickoIme);
    }
}
