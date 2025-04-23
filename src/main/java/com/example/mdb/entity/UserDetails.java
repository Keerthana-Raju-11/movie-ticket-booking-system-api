package com.example.mdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userid;  // Field name is 'userid' here

    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDate dateOfBirth;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean isDeleted;
    private Instant deletedAt;

    @Version
    private Integer version;

    public enum UserRole {
        USER,
        THEATER_OWNER
    }
}
