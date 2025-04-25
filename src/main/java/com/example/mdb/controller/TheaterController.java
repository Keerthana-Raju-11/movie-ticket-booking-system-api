package com.example.mdb.controller;

import com.example.mdb.dto.TheaterRequest;
import com.example.mdb.dto.TheaterResponse;
import com.example.mdb.entity.Theater;
import com.example.mdb.exception.TheaterNotFoundException;
import com.example.mdb.response.RestBuilder;
import com.example.mdb.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/theater")
public class TheaterController {

    private final TheaterService theaterService;
    private final RestBuilder restBuilder;

    private static final Logger logger = LoggerFactory.getLogger(TheaterController.class);

    // POST endpoint for creating a theater
    @PostMapping("/create")
    public ResponseEntity<?> createTheater(@RequestBody @Valid TheaterRequest request) {
        try {
            // Call service method to create theater
            TheaterResponse response = theaterService.createTheater(request);

            // Return success response with status and message
            return restBuilder.success(HttpStatus.CREATED, "Theater created successfully", response);
        } catch (Exception e) {
            // Log the exception for debugging
            logger.error("Error occurred while creating the theater: ", e);
            // Return an error response in case of failure
            return restBuilder.error(HttpStatus.BAD_REQUEST, "Failed to create theater", e.getMessage());
        }
    }

    // GET endpoint for fetching a theater by its ID
    @GetMapping("/theater")
    public ResponseEntity<?> getTheaterById(@PathVariable String id) {
        try {
            // Check if the ID is properly formatted as a UUID
            if (!id.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UUID format");
            }

            // Convert the ID from String to UUID format
            UUID theaterId = UUID.fromString(id);

            // Fetch the theater from the service
            Theater theater = theaterService.getTheaterById(theaterId);

            return ResponseEntity.ok(theater);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UUID format");
        }
    }
}

