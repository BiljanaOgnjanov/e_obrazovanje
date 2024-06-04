package com.example.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.backend.db.PredmetiRepo;
import com.example.backend.models.MojiPredmetiDTO;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class PredmetiController {
    
    @GetMapping("/svaPracenjaPredmeta/{username}")
    public List<MojiPredmetiDTO> svaPolaganja(@PathVariable("username") String username){
        return new PredmetiRepo().predmeti(username);
    }

    @PostMapping("/prijavaPredmeta/{username}")
    public boolean upisiNaPredmet(@PathVariable("username") String username){
        return true;
    }

}
