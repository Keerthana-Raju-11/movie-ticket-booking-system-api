package com.example.mdb.service;

import com.example.mdb.dto.RegisterRequest;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.entity.UserDetails.UserRole;
import com.example.mdb.repository.UserDetails.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse registerUser(RegisterRequest request) {
        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered.");
        }

        // Create a new user
        UserDetails user = new UserDetails();
        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        // Set the user role, convert string to enum
        user.setUserRole(UserRole.valueOf(request.getUserRole().toUpperCase())); // Ensure it's in uppercase

        // Set phone number if available, otherwise set an empty string or null
        user.setPhoneNumber(""); // You can update this logic based on how you want to handle phone numbers

        // Optionally handle dateOfBirth if it's part of RegisterRequest

        // Save the user to the database
        UserDetails savedUser = userRepository.save(user);

        // Return a UserResponse with relevant details
        return new UserResponse(
                savedUser.getUserid(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getUserRole().name(),
                savedUser.getPhoneNumber()
        );
    }
}
