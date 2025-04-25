package com.example.mdb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterRequest {
    private String name;
    private String address;
    private String city;
    private String landmark;
    private String email;
}
