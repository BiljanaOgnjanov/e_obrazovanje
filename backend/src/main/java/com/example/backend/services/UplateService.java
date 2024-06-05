package com.example.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.models.Uplate;
import com.example.backend.db.UplateRepo;

@Service
public class UplateService {
    
    @Autowired
    private UplateRepo uplateRepository;

    public List<Uplate> getUplateByUsername(String username) {
        return uplateRepository.findByKorisnickoIme(username);
    }
}
