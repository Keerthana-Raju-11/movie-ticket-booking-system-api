package com.example.mdb.repository.UserDetails;

import com.example.mdb.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TheaterRepository extends JpaRepository<Theater, UUID> {
    @Query("SELECT t FROM Theater t WHERE HEX(t.theaterId) = :theaterId")
    Optional<Theater> findByTheaterId(@Param("theaterId") String theaterId);
}

