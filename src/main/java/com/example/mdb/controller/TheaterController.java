package com.example.mdb.controller;

import com.example.mdb.dto.TheaterRequest;
import com.example.mdb.dto.TheaterResponse;
import com.example.mdb.dto.TheaterUpdateDTO;
import com.example.mdb.entity.Theater;
import com.example.mdb.exception.TheaterNotFoundException;
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
    private static final Logger logger = LoggerFactory.getLogger(TheaterController.class);

    // POST endpoint for creating a theater
    @PostMapping("/create")
    public ResponseEntity<?> createTheater(@RequestBody @Valid TheaterRequest request) {
        try {
            // Call service method to create theater
            TheaterResponse response = theaterService.createTheater(request);

            // Return success response with status and message
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Log the exception for debugging
            logger.error("Error occurred while creating the theater: ", e);
            // Return an error response in case of failure
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create theater: " + e.getMessage());
        }
    }

    // GET endpoint for fetching a theater by its ID
    @GetMapping
    public ResponseEntity<?> getTheaterById(@RequestParam String id) {
        try {
            // Convert the ID from String to UUID format
            UUID theaterId = UUID.fromString(id);  // Will throw IllegalArgumentException if invalid

            // Fetch the theater from the service
            Theater theater = theaterService.getTheaterById(theaterId);

            // Return the theater in the response body
            TheaterResponse response = new TheaterResponse(
                    theater.getTheaterId().toString(),
                    theater.getName(),
                    theater.getAddress(),
                    theater.getCity(),
                    theater.getLandmark(),
                    theater.getCreatedAt().toEpochMilli(),
                    theater.getUpdatedAt().toEpochMilli(),
                    theater.getCreatedBy().getEmail()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Handle invalid UUID format
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UUID format");
        } catch (TheaterNotFoundException e) {
            // Return 404 with a meaningful message if the theater is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // PUT endpoint for updating a theater
    @PutMapping("/{theaterId}")
    public ResponseEntity<TheaterResponse> updateTheater(@PathVariable UUID theaterId,
                                                         @RequestBody @Valid TheaterUpdateDTO theaterUpdateDTO) {
        // Call the service to update the theater
        Theater updatedTheater = theaterService.updateTheater(theaterId, theaterUpdateDTO);

        // Map the updated theater to a response DTO
        TheaterResponse response = new TheaterResponse(
                updatedTheater.getTheaterId().toString(),
                updatedTheater.getName(),
                updatedTheater.getAddress(),
                updatedTheater.getCity(),
                updatedTheater.getLandmark(),
                updatedTheater.getCreatedAt().toEpochMilli(),
                updatedTheater.getUpdatedAt().toEpochMilli(),
                updatedTheater.getCreatedBy().getEmail()
        );

        // Return the updated theater in the response
        return ResponseEntity.ok(response);

    }
}
