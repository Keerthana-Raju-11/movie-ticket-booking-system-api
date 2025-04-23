package com.example.mdb.service;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.entity.UserDetails.UserRole;
import com.example.mdb.entity.User;
import com.example.mdb.entity.TheaterOwner;
import org.springframework.stereotype.Service;

@Service
public class UserMapperService {

    public UserDetails mapToEntity(UserRegistrationDTO dto) {
        UserRole role = UserRole.valueOf(dto.getUserRole().toUpperCase());
        UserDetails user;

        if (role == UserRole.THEATER_OWNER) {
            user = new TheaterOwner();
        } else {
            user = new User();
        }

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // Consider hashing the password in real use cases
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUserRole(role);

        return user;
    }
}
