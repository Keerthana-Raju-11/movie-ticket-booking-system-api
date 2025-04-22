package com.example.mdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userid;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING) // or EnumType.ORDINAL
    private UserRole userRole;
    private LocalDate dateOfBirth;
    private long createdAt;
    private long updatedAt;

    public enum UserRole{
        USER,
        THEATER_OWNER
    }
}
