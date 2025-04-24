package com.example.mdb.service;

import com.example.mdb.entity.Theater;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.exception.UserNotFoundException;
import com.example.mdb.dto.TheaterRequest;
import com.example.mdb.dto.TheaterResponse;
import com.example.mdb.repository.UserDetails.TheaterRepository;
import com.example.mdb.repository.UserDetails.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final UserRepository userRepository;
    private final TheaterRepository theaterRepository;
    private static final Logger logger = LoggerFactory.getLogger(TheaterService.class);

    public TheaterResponse createTheater(TheaterRequest request, String email) {
        // Check if the user exists
        UserDetails user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

        // Create a new theater
        Theater theater = new Theater();
        theater.setName(request.getName());
        theater.setAddress(request.getAddress());
        theater.setCity(request.getCity());
        theater.setLandmark(request.getLandmark());
        theater.setUser(user); // Associate user (of type UserDetails) with theater

        try {
            // Save the theater to the repository
            theater = theaterRepository.save(theater);

            // Return the theater response with appropriate values
            return new TheaterResponse(
                    theater.getTheaterId(),
                    theater.getName(),
                    theater.getAddress(),
                    theater.getCity(),
                    theater.getLandmark(),
                    theater.getCreatedAt().toEpochMilli(),
                    theater.getUpdatedAt().toEpochMilli(),
                    theater.getCreatedBy().getEmail() // Assuming 'getCreatedBy' returns UserDetails with email
            );
        } catch (Exception e) {
            // Log and handle the exception
            logger.error("Error occurred while creating theater: ", e);
            throw new RuntimeException("An error occurred while creating the theater: " + e.getMessage());
        }
    }
}
