package com.example.mdb.service;

import com.example.mdb.dto.TheaterRequest;
import com.example.mdb.dto.TheaterResponse;
import com.example.mdb.dto.TheaterUpdateDTO;
import com.example.mdb.entity.Theater;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.exception.TheaterNotFoundException;
import com.example.mdb.exception.UserNotFoundException;
import com.example.mdb.repository.UserDetails.TheaterRepository;
import com.example.mdb.repository.UserDetails.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final UserRepository userRepository;
    private final TheaterRepository theaterRepository;
    private static final Logger logger = LoggerFactory.getLogger(TheaterService.class);

    @Transactional
    public TheaterResponse createTheater(TheaterRequest request) {
        // Get the email from the request
        String email = request.getEmail();

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
        theater.setCreatedBy(user); // Set createdBy to the user who is creating the theater

        Instant now = Instant.now();
        theater.setCreatedAt(now);
        theater.setUpdatedAt(now);

        try {
            // Save the theater to the repository
            theater = theaterRepository.save(theater);

            // Return the theater response with appropriate values
            return new TheaterResponse(
                    theater.getTheaterId().toString(),
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

    public Theater getTheaterById(UUID theaterId) {
        // Assuming the repository expects UUID, directly use UUID without converting to hex
        return theaterRepository.findById(theaterId)  // Assuming `theaterRepository.findById` is using UUID as the key
                .orElseThrow(() -> new TheaterNotFoundException("Theater with ID " + theaterId + " not found"));
    }

    @Transactional
    public Theater updateTheater(UUID theaterId , TheaterUpdateDTO theaterUpdateDTO){
        Theater theater = theaterRepository.findByTheaterId(theaterId)
                .orElseThrow(()-> new TheaterNotFoundException("Theater with ID"+theaterId+"not found"));

        if (theaterUpdateDTO.getName() != null) {
            theater.setName(theaterUpdateDTO.getName());
        }
        if (theaterUpdateDTO.getAddress() != null) {
            theater.setAddress(theaterUpdateDTO.getAddress());
        }
        if (theaterUpdateDTO.getCity() != null) {
            theater.setCity(theaterUpdateDTO.getCity());
        }
        if (theaterUpdateDTO.getLandmark() != null) {
            theater.setLandmark(theaterUpdateDTO.getLandmark());
        }

        // Update the updated_at timestamp to the current time
        theater.setUpdatedAt(Instant.now());

        // Save the updated theater back to the database
        return theaterRepository.save(theater);  // This should return the updated Theater entity
    }

}
