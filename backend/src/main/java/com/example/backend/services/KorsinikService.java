package com.example.backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Object prijava(String username, String password) {
        Korisnik user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return null;
        }

        if (user.getTip().equals("student")) {
            return studentRepository.findById(username).orElse(null);
        } else if (user.getTip().equals("nastavnik")) {
            return professorRepository.findById(username).orElse(null);
        }

        return null;
    }
    
}
