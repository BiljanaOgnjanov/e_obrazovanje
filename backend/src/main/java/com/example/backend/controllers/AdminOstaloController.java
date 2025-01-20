package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.Korisnik;
import com.example.backend.models.Nastavnik;
import com.example.backend.models.Student;
import com.example.backend.services.AdminService;
import com.example.backend.services.PlacanjaService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminOstaloController {

    @Autowired
    private PlacanjaService placanjaService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return adminService.getAllStudents();
    }

    @GetMapping("/professors")
    public List<Nastavnik> getAllProfessors() {
        return adminService.getAllProfessors();
    }

     @PostMapping("/add-student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = adminService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping("/add-professor")
    public ResponseEntity<Nastavnik> addProfessor(@RequestBody Nastavnik professor) {
        Nastavnik savedProfessor = adminService.saveProfessor(professor);
        return ResponseEntity.ok(savedProfessor);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody Korisnik user) {
        if (user instanceof Student) {
            Student updatedStudent = adminService.saveStudent((Student) user);
            return ResponseEntity.ok(updatedStudent);
        } else if (user instanceof Nastavnik) {
            Nastavnik updatedProfessor = adminService.saveProfessor((Nastavnik) user);
            return ResponseEntity.ok(updatedProfessor);
        }
        return ResponseEntity.badRequest().body("Unsupported user type");
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            adminService.deleteUser(username);
            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/resetOverenSemestar")
    public ResponseEntity<String> resetOverenSemestar() {
        try {
            System.out.println("ajde molim te proradi");
            placanjaService.resetOverenSemestarForAllStudents();
            return ResponseEntity.ok("Successfully reset 'overenSemestar' for all students.");
        } catch (Exception e) {
            System.out.println("nije proradilo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reset 'overenSemestar'.");
        }
    }
}
