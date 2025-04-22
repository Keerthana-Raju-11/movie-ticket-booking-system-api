package com.example.mdb.repository.UserDetails;

import com.example.mdb.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {
    boolean existsByEmail(String email);
}
