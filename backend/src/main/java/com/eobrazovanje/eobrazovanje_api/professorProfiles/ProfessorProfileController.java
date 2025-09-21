package com.eobrazovanje.eobrazovanje_api.professorProfiles;

import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.CreateProfessorProfileDto;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.ProfessorProfileDto;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.dto.UpdateProfessorProfileDto;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professors")
public class ProfessorProfileController {

    @Autowired
    private ProfessorProfileService professorProfileService;

    @GetMapping
    public ResponseEntity<List<ProfessorProfileDto>> getAll() {
        List<ProfessorProfileDto> users = professorProfileService.getAll();
        
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorProfileDto> getById (@PathVariable UUID id) {
        ProfessorProfileDto user = professorProfileService.getById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<ProfessorProfileDto> create(@Valid @RequestBody CreateProfessorProfileDto data) {
        ProfessorProfileDto user = professorProfileService.create(data);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorProfileDto> update(@PathVariable UUID id, @Valid @RequestBody UpdateProfessorProfileDto data) {
        ProfessorProfileDto user = professorProfileService.update(id, data);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        professorProfileService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
