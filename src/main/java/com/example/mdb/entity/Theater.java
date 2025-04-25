package com.example.mdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Theater {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "theater_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID theaterId;

    private String name;
    private String address;
    private String city;
    private String landmark;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp // Automatically set by Hibernate
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp // Automatically set by Hibernate
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserDetails createdBy;

    // Getters and Setters
}
