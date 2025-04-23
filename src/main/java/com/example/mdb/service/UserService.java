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

    public UserResponse registerUser(UserRegistrationDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        UserDetails user = userMapperService.mapToEntity(dto); // Map DTO to entity
        UserDetails savedUser = userRepository.save(user);

        return userMapperService.mapToResponse(savedUser); // Return mapped response
    }

}

