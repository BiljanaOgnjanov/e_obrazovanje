package com.eobrazovanje.eobrazovanje_api.auth;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eobrazovanje.eobrazovanje_api.auth.dto.AuthRequestDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto data) {
        return service.login(data)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity
                .status(401)
                .body(Map.of("error", "Invalid email or password")));
    }
}
