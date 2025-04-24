package com.example.mdb.service;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.repository.UserDetails.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // For password hashing

    // Register method for user
    public String register(UserRegistrationDTO userRegistrationDTO) {
        // Check if user with the same email already exists
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new RuntimeException("Email is already registered.");
        }

        // Create new User entity
        UserDetails newUser = new UserDetails();
        newUser.setUsername(userRegistrationDTO.getUsername());
        newUser.setEmail(userRegistrationDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // Hash the password before saving

        // Save user to the database
        userRepository.save(newUser);

        return "User successfully registered!";
    }
}
