package com.eobrazovanje.eobrazovanje_api.users;

import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.users.dto.CreateUserDto;
import com.eobrazovanje.eobrazovanje_api.users.dto.UpdateUserDto;
import com.eobrazovanje.eobrazovanje_api.users.dto.UserDto;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public List<UserDto> getAll() {
        return this.repository.findAll().stream().map(this::toDto).toList();
    }

    public UserDto getById(UUID id) {
        User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return toDto(user);
    }

    public UserDto create(CreateUserDto data) {
        User user = User.builder()
            .firstName(data.firstName())
            .lastName(data.lastName())
            .email(data.email())
            .password(encoder.encode(data.password()))
            .userType(data.userType())
            .build();

        return toDto(repository.save(user));
    }

    public UserDto update(UUID id, UpdateUserDto data) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (data.firstName() != null) {
            user.setFirstName(data.firstName());
        }

        if (data.lastName() != null) {
            user.setLastName(data.lastName());
        }

        if (data.password() != null) {
            user.setPassword(data.password());
        }

        return toDto(repository.save(user));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }

        repository.deleteById(id);
    }

    private UserDto toDto(User user) {
        return new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getUserType(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
