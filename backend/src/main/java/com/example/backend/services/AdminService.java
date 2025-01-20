package com.example.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.db.NastavnikRepo;
import com.example.backend.db.StudentRepo;
import com.example.backend.models.Nastavnik;
import com.example.backend.models.Student;

@Service
public class AdminService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private NastavnikRepo nastavnikRepo;

     public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

     public List<Nastavnik> getAllProfessors() {
        return nastavnikRepo.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    
    public Nastavnik saveProfessor(Nastavnik professor) {
        return nastavnikRepo.save(professor);
    }

    public void deleteUser(String username) {
        if (studentRepo.existsById(username)) {
            studentRepo.deleteById(username);
        } else if (nastavnikRepo.existsById(username)) {
            nastavnikRepo.deleteById(username);
        } else {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
    }
}
