package com.example.mdb.controller;

import com.example.mdb.dto.TheaterRequest;
import com.example.mdb.dto.TheaterResponse;
import com.example.mdb.response.RestBuilder;
import com.example.mdb.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;
    private final RestBuilder restBuilder;

    // POST endpoint for creating a theater
    @PostMapping("/theater") // Use @PostMapping for better readability
    public Object createTheater(@RequestBody @Valid TheaterRequest request) {
        try {
            // Call service method to create theater
            TheaterResponse response = theaterService.createTheater(request);

            // Return success response with status and message
            return restBuilder.success(HttpStatus.CREATED, "Theater created successfully", response);
        } catch (Exception e) {
            // Return an error response in case of failure
            return restBuilder.error(HttpStatus.BAD_REQUEST, "Failed to create theater", e.getMessage());
        }
    }
}
