package com.example.mdb.dto;

import com.example.mdb.entity.UserDetails.UserRole;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
}
