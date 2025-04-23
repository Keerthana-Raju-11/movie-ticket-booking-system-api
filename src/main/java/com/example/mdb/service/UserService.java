package com.example.mdb.service;

import com.example.mdb.dto.UserRegistrationRequest;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.TheaterOwner;
import com.example.mdb.entity.User;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.exception.DuplicateEmailException;
import com.example.mdb.mapper.UserMapper;
import com.example.mdb.repository.UserDetails.UserRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public UserResponse register(UserRegistrationRequest userRegistrationRequest) throws DuplicateEmailException {
        if (userRepository.existsByEmail(userRegistrationRequest.email())) {
            throw new DuplicateEmailException("Email already exists: " + userRegistrationRequest.email());
        }

        // Generate a UUID for the user and the current timestamp for creation and update
        long currentTime = System.currentTimeMillis();
        UserDetails registeredUser = null;

        switch (userRegistrationRequest.userRole()) {
            case USER -> {
                User user = new User();
                copyCommonFields(userRegistrationRequest, user, currentTime);
                registeredUser = user;
            }

            case THEATER_OWNER -> {
                TheaterOwner theaterOwner = new TheaterOwner();
                copyCommonFields(userRegistrationRequest, theaterOwner, currentTime);
                registeredUser = theaterOwner;
            }
        }

        try {
            UserDetails savedUser = userRepository.save(registeredUser);
            return UserMapper.toUserResponse(savedUser);  // Attempt to save the user
        } catch (OptimisticLockException e) {
            logger.error("Version conflict detected: {}", e.getMessage());
            throw new OptimisticLockException("Could not save user due to version conflict");
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred while registering the user", e);
        }
    }


    // Method to copy common fields from DTO to User or TheaterOwner entity
    private void copyCommonFields(UserRegistrationRequest source, UserDetails target, long currentTime) {

        target.setUsername(source.username());
        target.setEmail(source.email());
        target.setPassword(source.password());
        target.setPhoneNumber(source.phoneNumber());
        target.setUserRole(source.userRole());
        target.setCreatedAt(currentTime);
        target.setUpdatedAt(currentTime);
    }
}
