package com.example.mdb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theater {

    @Id
    private String theaterId = UUID.randomUUID().toString();
    private String name;
    private String address;
    private String city;
    private String landmark;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDetails createdBy;

    public void setUser(UserDetails userDetails) {
        this.createdBy = userDetails;  // Set createdBy to UserDetails
    }
}
