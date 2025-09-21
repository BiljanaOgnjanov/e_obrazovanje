package com.eobrazovanje.eobrazovanje_api.adminProfiles;

import com.eobrazovanje.eobrazovanje_api.adminProfiles.dto.CreateAdminProfileDto;
import com.eobrazovanje.eobrazovanje_api.adminProfiles.dto.AdminProfileDto;
import com.eobrazovanje.eobrazovanje_api.adminProfiles.dto.UpdateAdminProfileDto;

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
@RequestMapping("/admins")
public class AdminProfileController {

    @Autowired
    private AdminProfileService adminProfileService;

    @GetMapping
    public ResponseEntity<List<AdminProfileDto>> getAll() {
        List<AdminProfileDto> users = adminProfileService.getAll();
        
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminProfileDto> getById (@PathVariable UUID id) {
        AdminProfileDto user = adminProfileService.getById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<AdminProfileDto> create(@Valid @RequestBody CreateAdminProfileDto data) {
        AdminProfileDto user = adminProfileService.create(data);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminProfileDto> update(@PathVariable UUID id, @Valid @RequestBody UpdateAdminProfileDto data) {
        AdminProfileDto user = adminProfileService.update(id, data);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        adminProfileService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
