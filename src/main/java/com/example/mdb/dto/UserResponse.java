package com.example.mdb.dto;

import com.example.mdb.entity.UserDetails;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserResponse {
    private final UUID id;
    private final String username;
    private final String email;
    private final String userrole;
    private final String phoneNumber;

    // Constructor for custom UserResponse with specific parameters
    public UserResponse(UUID id, String username, String email, String userrole, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userrole = userrole;
        this.phoneNumber = phoneNumber;
    }

    // Constructor to create UserResponse from UserDetails entity
    public UserResponse(UserDetails updatedUser) {
        this.id = updatedUser.getId();
        this.username = updatedUser.getUsername();
        this.email = updatedUser.getEmail();
        this.userrole = updatedUser.getUserRole().name();  // Assuming userRole is an enum, converting to string
        this.phoneNumber = updatedUser.getPhoneNumber();
    }

    public UserResponse(String s) {
    }
}
