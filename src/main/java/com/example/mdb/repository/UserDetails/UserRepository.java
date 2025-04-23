package com.example.mdb.repository.UserDetails;

import com.example.mdb.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDetails, UUID> {

    Optional<UserDetails> findByUseridAndIsDeletedFalse(UUID userid);
}
