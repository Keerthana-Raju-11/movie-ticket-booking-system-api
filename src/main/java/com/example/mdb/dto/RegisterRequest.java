package com.example.mdb.dto;

import lombok.Data;

@Data
public class RegisterRequest{
    private String name;
    private String email;
    private String password;
    private String userRole;
    private String phoneNumber;

}