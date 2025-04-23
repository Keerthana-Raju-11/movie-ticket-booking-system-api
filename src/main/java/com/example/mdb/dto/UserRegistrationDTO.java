package com.example.mdb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class UserRegistrationDTO {
    @NonNull
    private final String username;

    @NonNull
    private final String email;

    @NonNull
    private final String password;

    private final String phoneNumber;
    private final String userRole; // userRole could be "USER" or "THEATER_OWNER"
}

