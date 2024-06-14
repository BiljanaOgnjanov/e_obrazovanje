package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.db.NastavnikRepo;
import com.example.backend.db.PredavanjePredmetaRepo;
import com.example.backend.db.PredmetiAPRepo;
import com.example.backend.models.Nastavnik;
import com.example.backend.models.PredavanjePredmeta;
import com.example.backend.models.Predmet;

import java.util.List;

@Service
public class PredavanjePredmetaService {

    @Autowired
    private PredavanjePredmetaRepo predavanjePredmetaRepo;

    @Autowired
    private PredmetiAPRepo predmetiAPRepo;

    @Autowired 
    private NastavnikRepo nastavnikRepo;

    public PredavanjePredmeta addPredavanjePredmeta(String korisnickoIme, int idPredmeta) {
        Nastavnik nastavnik = nastavnikRepo.findById(korisnickoIme)
            .orElseThrow(() -> new IllegalArgumentException("Neispravno korisnicko ime nastavnika: " + korisnickoIme));
        Predmet predmet = predmetiAPRepo.findById(idPredmeta)
            .orElseThrow(() -> new IllegalArgumentException("Neispravan id predmeta: " + idPredmeta));
        
        PredavanjePredmeta predavanjePredmeta = new PredavanjePredmeta(nastavnik.getUsername(), predmet);
        return predavanjePredmetaRepo.save(predavanjePredmeta);
    }

    public List<Predmet> getCoursesByProfessorUsername(String username) {
        return predavanjePredmetaRepo.findCoursesByProfessorUsername(username);
    }

    public List<Nastavnik> getProfessorsNotTeachingCourse(int idPredmeta) {
        return predavanjePredmetaRepo.findProfessorsNotTeachingCourse(idPredmeta);
    }

    public List<Nastavnik> getProfessorsTeachingCourse(int idPredmeta) {
        return predavanjePredmetaRepo.findProfessorsTeachingCourse(idPredmeta);
    }
}
