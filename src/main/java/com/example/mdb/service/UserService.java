package com.example.mdb.service;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.repository.UserDetails.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ Use the interface, not the class

    public String register(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new RuntimeException("Email is already registered.");
        }

        UserDetails newUser = new UserDetails();
        newUser.setUsername(userRegistrationDTO.getUsername());
        newUser.setEmail(userRegistrationDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        userRepository.save(newUser);
        return "User successfully registered!";
    }
}
