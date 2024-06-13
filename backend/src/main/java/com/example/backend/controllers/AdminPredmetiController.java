package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.models.Predmet;
import com.example.backend.services.PredmetiService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminPredmetiController {

    @Autowired
    private PredmetiService predmetiService;

    
    @GetMapping("/predmeti")
    public List<Predmet> getAllCourses() {
        return predmetiService.getAllCourses();
    }

    @PostMapping("/predmeti")
    public Predmet createCourse(@RequestBody Predmet predmet) {
        return predmetiService.createCourse(predmet);
    }

    @DeleteMapping("/predmeti/{id}")
    public void deleteCourse(@PathVariable("id") int id) {
        predmetiService.deleteCourse(id);
    }
}
