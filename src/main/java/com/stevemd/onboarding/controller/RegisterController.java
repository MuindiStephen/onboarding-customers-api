package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.payload.UserDTO;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class RegisterController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    /**
     *

     *          * Perform these before steps first attempting to sign up the user
     *          *
     *          * @ControlStatements
     *          * 1.userRepository.existsByName(signUpRequest.getName())
     *          * 2.userRepository.existsByEmail(signUpRequest.getEmail())

     * @param signUpRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (userRepository.existsByName(signUpRequest.getName())) {
            return new ResponseEntity<>("Name is already taken", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is taken and is already in use by another account",HttpStatus.BAD_REQUEST);
        }

        // Now create user's account
        // UserDTO is the API returns after signup of the new user
        UserDTO signUpUser = authService.signUpUser(signUpRequest);

        if (signUpUser == null) {
            return new ResponseEntity<>("Failed to sign up user, please try again", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("User Registered successfully");
        // return new ResponseEntity<>("User Registered Successfully", HttpStatus.BAD_REQUEST);
    }
}
