package com.example.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend.db.PredmetiAPRepo;
import com.example.backend.db.PredmetiRepo;
import com.example.backend.models.MojiPredmetiDTO;
import com.example.backend.models.Predmet;

import org.springframework.stereotype.Service;

@Service
public class PredmetiService {

    @Autowired
    private PredmetiRepo predmetiRepository;

    @Autowired
    private PredmetiAPRepo predmetiAPRepo;

    
    public List<MojiPredmetiDTO> getPredmetiByKorisnickoIme(String korisnickoIme) {
        return predmetiRepository.findPredmetiByKorisnickoIme(korisnickoIme);
    }

     public List<Predmet> getAllCourses() {
        return predmetiAPRepo.findAll();
    }

    public Predmet createCourse(Predmet predmet) {
        return predmetiAPRepo.save(predmet);
    }

    public void deleteCourse(int id) {
        predmetiAPRepo.deleteById(id);
    }
}
