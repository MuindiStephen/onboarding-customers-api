package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.dto.LoginRequest;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthService authService;


    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        Authentication userLogin = authService.signInUser(loginRequest);

        if (userLogin.isAuthenticated()) {
            return new ResponseEntity<>(loginRequest.getEmail()+": logged in successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to login.", HttpStatus.BAD_REQUEST);
    }
}

