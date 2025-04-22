package com.example.mdb.service;

import com.example.mdb.entity.TheaterOwner;
import com.example.mdb.entity.User;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.exception.DuplicateEmailException;
import com.example.mdb.repository.UserDetails.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetails register(UserDetails userDetails) throws DuplicateEmailException {
        if (userRepository.existsByEmail(userDetails.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + userDetails.getEmail());
        }

        if (userDetails.getUserid() == null) {
            userDetails.setUserid(UUID.randomUUID());
        }

        userDetails.setCreatedAt(System.currentTimeMillis());
        userDetails.setUpdatedAt(System.currentTimeMillis());

        UserDetails registeredUser = null;

        switch (userDetails.getUserRole()) {
            case USER -> {
                User user = new User();
                copyCommonFields(userDetails, user);
                registeredUser = user;
            }

            case THEATER_OWNER -> {
                TheaterOwner theaterOwner = new TheaterOwner();
                copyCommonFields(userDetails, theaterOwner);
                registeredUser = theaterOwner;
            }
        }

        return userRepository.save(registeredUser);
    }

    private void copyCommonFields(UserDetails source, UserDetails target) {
        target.setUserid(source.getUserid());
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setUserRole(source.getUserRole());
        target.setDateOfBirth(source.getDateOfBirth());
        target.setCreatedAt(source.getCreatedAt());
        target.setUpdatedAt(source.getUpdatedAt());
    }
}
