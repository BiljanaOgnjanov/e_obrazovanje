package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.models.Nastavnik;
import com.example.backend.models.PredavanjePredmeta;
import com.example.backend.models.Predmet;
import com.example.backend.services.PredavanjePredmetaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profesori")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesorPredavanjePredmetaController {

    @Autowired
    private PredavanjePredmetaService predavanjePredmetaService;

    @GetMapping("/predmeti/{username}")
    public List<Predmet> getCoursesByProfessor(@PathVariable("username") String username) {
        return predavanjePredmetaService.getCoursesByProfessorUsername(username);
    }

    @PostMapping("/dodajPracenje")
    public ResponseEntity<PredavanjePredmeta> addPredavanjePredmeta(
        @RequestBody Map<String, Object> requestBody) {
        
        String korisnickoIme = (String) requestBody.get("korisnickoIme");
        int idPredmeta = (Integer) requestBody.get("idPredmeta");
        
        PredavanjePredmeta newPredavanjePredmeta = predavanjePredmetaService.addPredavanjePredmeta(korisnickoIme, idPredmeta);
        return new ResponseEntity<>(newPredavanjePredmeta, HttpStatus.CREATED);
    }

    @GetMapping("/nePredaju/{idPredmeta}")
    public List<Nastavnik> getProfessorsNotTeachingCourse(@PathVariable("idPredmeta") int idPredmeta) {
        return predavanjePredmetaService.getProfessorsNotTeachingCourse(idPredmeta);
    }

    @GetMapping("/predaju/{idPredmeta}")
    public List<Nastavnik> getProfessorsTeachingCourse(@PathVariable("idPredmeta") int idPredmeta) {
        return predavanjePredmetaService.getProfessorsTeachingCourse(idPredmeta);
    }
}
