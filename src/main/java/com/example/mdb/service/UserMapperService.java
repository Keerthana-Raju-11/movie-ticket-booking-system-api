package com.example.mdb.service;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapperService {
    public UserResponse mapToResponse(UserDetails user) {
        return new UserResponse(
                user.getUserid(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole().name(),
                user.getPhoneNumber()
        );
    }

    public UserDetails mapToEntity(UserRegistrationDTO dto) {
        UserDetails user = new UserDetails();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUserRole(UserDetails.UserRole.valueOf(dto.getUserRole().toUpperCase()));
        return user;
    }
}
