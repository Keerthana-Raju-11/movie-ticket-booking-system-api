package com.example.mdb.controller;

import com.example.mdb.dto.UserRequest;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.exception.UserNotFoundException;
import com.example.mdb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * PUT endpoint to update user profile details.
     * @param email The email of the user to be updated (provided in the query string).
     * @param request The data (name, phone number) to update (provided in the request body).
     * @return ResponseEntity with updated user information or error message.
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(
            @RequestParam("email") String email,  // User email for identification
            @RequestBody @Valid UserRequest request  // Profile data to update
    ) {
        // Retrieve user by email
        UserDetails user = userService.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }

        // Map UserRequest data to UserDetails entity
        if (request.getName() != null) {
            user.setUsername(request.getName());  // Set 'name' to 'username'
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        // Save the updated user details and return the response
        try {
            UserDetails updatedUser = userService.save(user);
            UserResponse userResponse = new UserResponse(updatedUser);  // Map to DTO
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            // Handle error separately for better clarity
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user profile due to server error.");
        }
    }
}
