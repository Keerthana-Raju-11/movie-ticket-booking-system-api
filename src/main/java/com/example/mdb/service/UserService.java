package com.example.mdb.service;

import com.example.mdb.dto.RegisterRequest;
import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.repository.UserDetails.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapperService userMapperService;

    public UserResponse registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Check if email already exists
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered.");
        }

        // Map the DTO to the respective entity
        UserDetails user = userMapperService.mapToEntity(userRegistrationDTO);

        // Persist the user
        UserDetails savedUser = userRepository.save(user);

        // Return a response DTO
        return new UserResponse(savedUser);
    }
}
