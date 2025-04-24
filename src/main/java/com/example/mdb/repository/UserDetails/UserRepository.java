package com.example.mdb.repository.UserDetails;

import com.example.mdb.entity.UserDetails;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDetails, UUID> {

    Optional<UserDetails> findByUseridAndIsDeletedFalse(UUID userid);
    Optional<UserDetails> findByEmail(String email);

    boolean existsByEmail(@NotBlank(message = "Email cannot be null") @Email(message = "Invalid email format") String email);
}
