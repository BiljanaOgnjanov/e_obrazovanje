package com.example.backend.services;

import java.util.List;
import java.util.Optional;

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

    public Optional<Predmet> getCourseById(int idPredmeta) {
        return predmetiAPRepo.findById(idPredmeta);
    }

    public Predmet updateCourse(int idPredmeta, Predmet updatedPredmet) {
        return predmetiAPRepo.findById(idPredmeta).map(predmet -> {
            predmet.setImePredmeta(updatedPredmet.getImePredmeta());
            predmet.setGodina(updatedPredmet.getGodina());
            predmet.setBrojKredita(updatedPredmet.getBrojKredita());
            predmet.setOpisPredmeta(updatedPredmet.getOpisPredmeta());
            return predmetiAPRepo.save(predmet);
        }).orElse(null);
    }
}
