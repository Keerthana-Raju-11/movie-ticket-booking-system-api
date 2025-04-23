package com.example.mdb.dto;


import com.example.mdb.entity.UserDetails;

public record UserRegistrationRequest(
        String username,
        String email,
        String password,
        String phoneNumber,
        UserDetails.UserRole userRole
) {}
