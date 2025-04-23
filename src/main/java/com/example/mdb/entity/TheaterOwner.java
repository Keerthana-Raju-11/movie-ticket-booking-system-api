package com.example.mdb.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("THEATER_OWNER")
public class TheaterOwner extends UserDetails{
}
