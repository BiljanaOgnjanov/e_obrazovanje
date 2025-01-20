package com.example.backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.db.AdministratorRepo;
import com.example.backend.db.KorisnikRepo;
import com.example.backend.db.NastavnikRepo;
import com.example.backend.db.StudentRepo;
import com.example.backend.models.Korisnik;

@Service
public class KorsinikService {

    @Autowired
    private KorisnikRepo userRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private NastavnikRepo professorRepository;

    @Autowired
    private AdministratorRepo administratorRepository;

    public Object prijava(String username, String password) {
        Korisnik user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return null;
        }

        switch (user.getTip()) {
            case "student":
                return studentRepository.findById(username).orElse(null);
            case "nastavnik":
                return professorRepository.findById(username).orElse(null);
            case "administrator":
                return administratorRepository.findById(username).orElse(null);
            default:
                return null;
        }
    }
    
}
