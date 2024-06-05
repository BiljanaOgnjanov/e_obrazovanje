package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.Korisnik;
import com.example.backend.services.KorsinikService;


@RestController
@RequestMapping("/korisnici")
@CrossOrigin(origins = "http://localhost:4200")
public class KorisniciController {

    @Autowired
    private KorsinikService korsinikService;

    @PostMapping("/prijava")
    public ResponseEntity<?> prijava(@RequestBody Korisnik korisnik) {
        System.out.println(korisnik.getUsername());
        Object user = korsinikService.prijava(korisnik.getUsername(), korisnik.getLozinka());
        if (user == null) {
            return null;
        }
        return ResponseEntity.ok(user);
    }
}
