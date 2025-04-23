package com.example.mdb.service;

import com.example.mdb.dto.UserRequest;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.repository.UserDetails.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapperService userMapperService;

//    public UserResponse updateUser(String email, UserRequest userRequest) {
//        // Find the user by email
//        UserDetails user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Update the fields
//        user.setUsername(userRequest.getUsername());
//        user.setPhoneNumber(userRequest.getPhoneNumber());
//
//        // Save the updated user
//        UserDetails updatedUser = userRepository.save(user);
//
//        // Map the updated user to a response DTO
//        return userMapperService.mapToResponse(updatedUser);
//    }

    public void softDeleteUser(UUID userid) {
        UserDetails user = userRepository.findByUseridAndIsDeletedFalse(userid)
                .orElseThrow(() -> new RuntimeException("User not found or already deleted"));

        user.setDeleted(true);
        user.setDeletedAt(Instant.now());

        userRepository.save(user);
    }

    public Optional<UserDetails> getActiveUserById(UUID id){
        return userRepository.findByUseridAndIsDeletedFalse(id);
    }
}
