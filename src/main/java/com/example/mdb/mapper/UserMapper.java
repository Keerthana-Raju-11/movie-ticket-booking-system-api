package com.example.mdb.mapper;

import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;

public class UserMapper {

    public static UserResponse toUserResponse(UserDetails userDetails){
        return new UserResponse(
                userDetails.getUserid(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getUserRole().name(),
                userDetails.getPhoneNumber(),
                userDetails.getUserRole()
        );
    }
}
