package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.backend.models.MojiPredmetiDTO;
import com.example.backend.services.PredmetiService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class PredmetiController {

    @Autowired
    private PredmetiService predmetiService;
    
    @GetMapping("/svaPracenjaPredmeta/{username}")
    public List<MojiPredmetiDTO> svaPolaganja(@PathVariable("username") String username){
        return  predmetiService.getPredmetiByKorisnickoIme(username);
    }

}
