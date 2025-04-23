package com.example.mdb.dto;

import lombok.Getter;

import java.util.UUID;
@Getter
public class UserResponse {
    private final UUID id;
    private final String username;
    private final String email;
    private final String userrole;
    private final String phoneNumber;


    public UserResponse(UUID id,String username, String email,String userrole , String phoneNumber){
        this.id = id;
        this.username = username;
        this.email = email;
        this.userrole = userrole;
        this.phoneNumber = phoneNumber;
    }
    
}
