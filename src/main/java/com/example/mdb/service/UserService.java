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

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Registers a new user.
     * @param userRegistrationRequest the user registration request.
     * @return the user response after registration.
     * @throws DuplicateEmailException if the email already exists.
     */
    public UserResponse register(UserRegistrationRequest userRegistrationRequest) throws DuplicateEmailException {
        if (userRepository.existsByEmail(userRegistrationRequest.email())) {
            throw new DuplicateEmailException("Email already exists: " + userRegistrationRequest.email());
        }

        long currentTime = System.currentTimeMillis();
        UserDetails registeredUser = null;

        switch (userRegistrationRequest.userRole()) {
            case USER:
                registeredUser = createUser(userRegistrationRequest, currentTime);
                break;
            case THEATER_OWNER:
                registeredUser = createTheaterOwner(userRegistrationRequest, currentTime);
                break;
            default:
                throw new IllegalArgumentException("Invalid user role");
        }

        try {
            UserDetails savedUser = userRepository.save(registeredUser);
            return UserMapper.toUserResponse(savedUser);
        } catch (OptimisticLockException e) {
            logger.error("Version conflict detected: {}", e.getMessage());
            throw new OptimisticLockException("Could not save user due to version conflict");
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred while registering the user", e);
        }
    }

    /**
     * Creates a new User entity.
     * @param userRegistrationRequest the user registration request.
     * @param currentTime the current timestamp.
     * @return the created User entity.
     */
    private UserDetails createUser(UserRegistrationRequest userRegistrationRequest, long currentTime) {
        User user = new User();
        copyCommonFields(userRegistrationRequest, user, currentTime);
        return user;
    }

    /**
     * Creates a new TheaterOwner entity.
     * @param userRegistrationRequest the user registration request.
     * @param currentTime the current timestamp.
     * @return the created TheaterOwner entity.
     */
    private UserDetails createTheaterOwner(UserRegistrationRequest userRegistrationRequest, long currentTime) {
        TheaterOwner theaterOwner = new TheaterOwner();
        copyCommonFields(userRegistrationRequest, theaterOwner, currentTime);
        return theaterOwner;
    }

    /**
     * Copies common fields from the registration request to the entity.
     * @param source the source DTO containing registration information.
     * @param target the target entity to copy fields to.
     * @param currentTime the current timestamp for createdAt and updatedAt fields.
     */
    private void copyCommonFields(UserRegistrationRequest source, UserDetails target, long currentTime) {
        target.setUsername(source.username());
        target.setEmail(source.email());
        target.setPassword(source.password());
        target.setPhoneNumber(source.phoneNumber());
        target.setUserRole(source.userRole());
        target.setCreatedAt(currentTime);
        target.setUpdatedAt(currentTime);
    }

    /**
     * Finds a user by email.
     * @param email the email of the user.
     * @return the UserDetails entity or null if not found.
     */
    public UserDetails findByEmail(String email) {
        Optional<UserDetails> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    /**
     * Updates a user's profile information.
     * @param user the existing UserDetails entity.
     * @param name the new name (can be null).
     * @param phoneNumber the new phone number (can be null).
     * @return the updated UserResponse.
     */
    public UserResponse updateUser(UserDetails user, String name, String phoneNumber) {
        if (name != null) {
            user.setUsername(name);
        }
        if (phoneNumber != null) {
            user.setPhoneNumber(phoneNumber);
        }

        // Update the 'updatedAt' timestamp
        user.setUpdatedAt(System.currentTimeMillis());

        // Save the updated user and return the response DTO
        UserDetails updatedUser = userRepository.save(user);
        return UserMapper.toUserResponse(updatedUser);
    }

    /**
     * Saves a user entity.
     * @param userDetails the UserDetails entity to be saved.
     * @return the saved UserDetails entity.
     */
    public UserDetails save(UserDetails userDetails) {
        try {
            return userRepository.save(userDetails);
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw new RuntimeException("An error occurred while saving the user", e);
        }
    }
}
