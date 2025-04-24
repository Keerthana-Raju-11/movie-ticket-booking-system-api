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
@RequestMapping("/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;
    private final RestBuilder restBuilder;

    // POST endpoint for creating a theater
    @PostMapping
    public Object createTheater(@RequestBody @Valid TheaterRequest request, @RequestParam String email) {
        // Call service method to create theater
        TheaterResponse response = theaterService.createTheater(request, email);

        // Return success response with status and message
        return restBuilder.success(HttpStatus.CREATED, "Theater created successfully", response);
    }
}
