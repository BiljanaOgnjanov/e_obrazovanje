package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.models.Predmet;
import com.example.backend.services.PredmetiService;

import java.util.Optional;

@RestController
@RequestMapping("/profesori")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesorPredmetiController {
    @Autowired
    private PredmetiService predmetiService;

    @GetMapping("/jedanPredmet/{idPredmeta}")
    public ResponseEntity<Predmet> getCourseById(@PathVariable int idPredmeta) {
        Optional<Predmet> predmet = predmetiService.getCourseById(idPredmeta);
        return predmet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/jedanPredmet/{idPredmeta}")
    public ResponseEntity<Predmet> updateCourse(@PathVariable int idPredmeta, @RequestBody Predmet updatedPredmet) {
            Predmet updated = predmetiService.updateCourse(idPredmeta, updatedPredmet);
            return ResponseEntity.ok(updated);
    }
}
