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

    // Constructor to convert UserDetails entity to DTO
    public UserResponse(UserDetails user) {
        this.id = user.getUserid();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userrole = user.getUserRole().name(); // Correctly map userRole from enum
        this.phoneNumber = user.getPhoneNumber();  // Ensure phone number is properly set
    }

    // Constructor to accept individual fields
    public UserResponse(UUID id, String username, String email, String userrole, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userrole = userrole;
        this.phoneNumber = phoneNumber;
    }
}
