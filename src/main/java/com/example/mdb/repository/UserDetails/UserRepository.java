package com.example.mdb.repository.UserDetails;

import com.example.mdb.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, UUID> {
    boolean existsByEmail(String email);
}
