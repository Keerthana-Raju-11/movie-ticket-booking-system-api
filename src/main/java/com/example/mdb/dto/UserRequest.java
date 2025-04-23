package com.example.mdb.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String phoneNumber;
    private String Email;
    private String Password;
    private String userRole;
}
