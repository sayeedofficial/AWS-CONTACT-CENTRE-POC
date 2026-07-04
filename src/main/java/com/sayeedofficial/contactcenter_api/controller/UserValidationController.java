package com.sayeedofficial.contactcenter_api.controller;

import com.sayeedofficial.contactcenter_api.dto.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserValidationController {

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public CreateUserRequest validateCreateUserRequest(
            @Valid @RequestBody CreateUserRequest request
    ) {
        return request;
    }
}