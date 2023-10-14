package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.dto.SignUpRequest;
import com.stevemd.onboarding.dto.UserDTO;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {

        /**
         * Perform these before attempting to sign up the user
         */
        if (userRepository.existsByName(signUpRequest.getName())) {
            return new ResponseEntity<>("Name is already taken", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is taken and is already in use by another account",HttpStatus.BAD_REQUEST);
        }
        UserDTO signUpUser = authService.signUpUser(signUpRequest);

        if (signUpUser == null) {
            return new ResponseEntity<>("Failed to sign up user, please try again", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(signUpUser+" User Registered successfully",HttpStatus.CREATED);
    }
}
