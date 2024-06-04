package com.example.backend.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.backend.db.UplateRepo;
import com.example.backend.models.Dokument;
import com.example.backend.models.Uplate;



@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class UplateDokumentaController {
    
    @GetMapping("/uplate/{username}")
    public List<Uplate> uplateKorisnika(@PathVariable("username") String username) {
        return new UplateRepo().mojeUplate(username);
    }
    
    @GetMapping("/dokumenti/{username}")
    public List<Dokument> dokumentiKorisnika(@PathVariable("username") String username) {
        return new UplateRepo().mojiDokumenti(username);
    }
    

}
