package com.eobrazovanje.eobrazovanje_api.auth;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eobrazovanje.eobrazovanje_api.auth.dto.AuthRequestDto;
import com.eobrazovanje.eobrazovanje_api.auth.dto.AuthResponseDto;
import com.eobrazovanje.eobrazovanje_api.users.User;
import com.eobrazovanje.eobrazovanje_api.users.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository repository, PasswordEncoder encoder) {
        this.userRepository = repository;
        this.encoder = encoder;
    }

    public Optional<AuthResponseDto> login(AuthRequestDto data) {
        return userRepository.findByEmail(data.email())
            .filter(user -> encoder.matches(data.password(), user.getPassword()))
            .map(this::toDto);
    }

    private AuthResponseDto toDto(User user) {
        return new AuthResponseDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getUserType().name()
        );
    }
}
