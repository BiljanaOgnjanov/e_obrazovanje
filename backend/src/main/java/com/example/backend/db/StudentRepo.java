package com.example.backend.db;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Student;



@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
    
    @Query("SELECT s FROM Student s WHERE s.username = :korisnickoIme")
    Optional<Student> findByKorisnickoIme(@Param("korisnickoIme") String korisnickoIme);

    @Modifying
    @Query(value = "UPDATE studenti SET overen_semestar = 'neoveren'", nativeQuery = true)
    void resetOverenSemestarForAllStudents();
}

