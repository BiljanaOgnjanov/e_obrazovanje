package com.example.backend.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.models.Placanja;
import com.example.backend.models.Student;

import jakarta.transaction.Transactional;

import com.example.backend.db.PlacanjaRepo;
import com.example.backend.db.StudentRepo;

@Service
public class PlacanjaService {
    
    @Autowired
    private PlacanjaRepo placanjaRepository;

    @Autowired
    private StudentRepo studentRepo;

    public List<Placanja> getPlacanjaByUsername(String username) {
        return placanjaRepository.findByKorisnickoIme(username);
    }
    

    @Transactional
    public void resetOverenSemestarForAllStudents() {
        System.out.println("Executing resetOverenSemestarForAllStudents query");
        studentRepo.resetOverenSemestarForAllStudents();
    }

    public String overiSemestar(String korisnickoIme) {
        // Fetch the student
        Optional<Student> studentOpt = studentRepo.findByKorisnickoIme(korisnickoIme);
        if (!studentOpt.isPresent()) {
            return "Korisnik nije pronađen.";
        }

        Student student = studentOpt.get();
        if (student.getOverenSemestar().equals("overen")) {
            return "Semestar je već overen.";
        }

        if (student.getRacun() < 2000) {
            return "Nemate dovoljno novca na računu.";
        }

        // Deduct amount and update student's account
        student.setRacun(student.getRacun() - 2000);
        student.setOverenSemestar("overen");
        studentRepo.save(student);

        // Record the payment
        Placanja placanje = new Placanja();
        placanje.setNazivPlacanja("Overa semestra");
        placanje.setKorisnickoIme(korisnickoIme);
        placanje.setIznos(2000);
        placanje.setDatum(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        placanje.setKomentar("Semestar uspešno overen.");
        placanjaRepository.save(placanje);

        return "Uspešno ste overili semestar.";
    }
}
