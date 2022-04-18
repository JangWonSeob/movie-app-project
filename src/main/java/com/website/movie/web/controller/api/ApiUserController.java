package com.website.movie.web.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiUserController {

    @PostMapping("/api/user/gets.api")
    public String gets() {
        return "";
    }


}
