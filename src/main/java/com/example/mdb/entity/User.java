package com.example.mdb.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("USER")
public class User extends UserDetails{
}
