package com.example.mdb.service;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.repository.UserDetails.UserRepository;
import com.example.mdb.exception.UserAlreadyExistsException;
import com.example.mdb.security.BCryptPasswordEncoder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // Spring Security's BCryptPasswordEncoder

    // Register a new user
    @Transactional
    public void registerUser(@Valid UserRegistrationDTO userRegistrationDTO) {
        Optional<UserDetails> existingUser = userRepository.findByEmail(userRegistrationDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userRegistrationDTO.getEmail() + " already exists.");
        }

        // Map DTO to entity
        UserDetails userDetails = mapToUserDetails(userRegistrationDTO);

        // Custom date parsing logic (for MM/dd/yyyy format)
        @NotNull(message = "Date of birth cannot be null") @Past(message = "Date of birth must be in the past") LocalDate dateOfBirthStr = userRegistrationDTO.getDateOfBirth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Use the correct format
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr, formatter);
        userDetails.setDateOfBirth(dateOfBirth);

        // Encrypt password before saving
        userDetails.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        // Save user in the database
        userRepository.save(userDetails);
    }

    // Soft delete user by email
    @Transactional
    public boolean softDeleteUserByEmail(String email) {
        Optional<UserDetails> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            UserDetails user = userOpt.get();
            user.setDeleted(true);
            user.setDeletedAt(Instant.now());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // Method to map DTO to Entity
    private UserDetails mapToUserDetails(UserRegistrationDTO userRegistrationDTO) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(userRegistrationDTO.getUsername());
        userDetails.setEmail(userRegistrationDTO.getEmail());
        userDetails.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        userDetails.setUserRole(UserDetails.UserRole.valueOf(userRegistrationDTO.getUserRole()));
        userDetails.setCreatedAt(LocalDateTime.now());
        userDetails.setUpdatedAt(LocalDateTime.now());
        return userDetails;
    }
}
