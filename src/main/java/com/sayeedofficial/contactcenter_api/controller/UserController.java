package com.sayeedofficial.contactcenter_api.controller;

import com.sayeedofficial.contactcenter_api.dto.CreateUserRequest;
import com.sayeedofficial.contactcenter_api.dto.UserResponse;
import com.sayeedofficial.contactcenter_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(201).body(response);
    }
}
