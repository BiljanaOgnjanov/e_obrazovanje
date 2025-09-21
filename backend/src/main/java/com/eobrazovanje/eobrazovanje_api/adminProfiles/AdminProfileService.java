package com.eobrazovanje.eobrazovanje_api.adminProfiles;

import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.adminProfiles.dto.CreateAdminProfileDto;
import com.eobrazovanje.eobrazovanje_api.adminProfiles.dto.AdminProfileDto;
import com.eobrazovanje.eobrazovanje_api.adminProfiles.dto.UpdateAdminProfileDto;
import com.eobrazovanje.eobrazovanje_api.users.User;
import com.eobrazovanje.eobrazovanje_api.users.UserRepository;
import com.eobrazovanje.eobrazovanje_api.users.UserType;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminProfileService {
    private final AdminProfileRepository adminProfileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AdminProfileService(
        AdminProfileRepository adminProfileRepository,
        UserRepository userRepository,
        PasswordEncoder encoder
    ) {
        this.adminProfileRepository = adminProfileRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<AdminProfileDto> getAll() {
        return this.adminProfileRepository.findAll().stream().map(this::toDto).toList();
    }

    public AdminProfileDto getById(UUID id) {
        AdminProfile user = this.adminProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin profile not found"));

        return toDto(user);
    }

    public AdminProfileDto create(CreateAdminProfileDto data) {
        User user = User.builder()
            .firstName(data.firstName())
            .lastName(data.lastName())
            .email(data.email())
            .password(encoder.encode(data.password()))
            .userType(UserType.ADMIN)
            .build();
        
        userRepository.save(user);

        AdminProfile profile = AdminProfile.builder()
            .user(user)
            .role(data.role())
            .build();

        adminProfileRepository.save(profile);

        return toDto(profile);
    }

    public AdminProfileDto update(UUID id, UpdateAdminProfileDto data) {
        AdminProfile profile = adminProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin profile not found"));

        User user = profile.getUser();

        if (data.firstName() != null) {
            user.setFirstName(data.firstName());
        }

        if (data.lastName() != null) {
            user.setLastName(data.lastName());
        }

        if (data.password() != null) {
            user.setPassword(data.password());
        }

        userRepository.save(user);
        return toDto(profile);
    }

    public void delete(UUID id) {
        if (!adminProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admin profile not found");
        }

        adminProfileRepository.deleteById(id);
    }

    private AdminProfileDto toDto(AdminProfile user) {
        return new AdminProfileDto(
            user.getUser().getId(),
            user.getUser().getFirstName(),
            user.getUser().getLastName(),
            user.getUser().getEmail(),
            user.getRole(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
