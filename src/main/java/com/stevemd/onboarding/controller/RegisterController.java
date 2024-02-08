package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.model.Role;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.model.User_Role;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.payload.response.MessageResponse;
import com.stevemd.onboarding.repository.RoleRepository;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin("*")
@RestController

@RequestMapping("/api/v1/api")
public class RegisterController {

    @Autowired
    private AuthService authService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByName(signUpRequest.getName())) {
           // return new ResponseEntity<>("Username is already taken! Try a new username",HttpStatus.BAD_REQUEST);
            return ResponseEntity
                    //.status(HttpStatus.BAD_REQUEST)
                    .badRequest()
                    .body("Username is already taken, Try a new username");
            // TODO - implement json here as return type
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is taken and already in use by another account");
            // TODO - implement json here as return type
        }

        // Now create user's account
        // UserDTO is the API returns after signup of the new user
        User signUpUser = authService.signUpUser(signUpRequest);


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        for (String role : strRoles) {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(User_Role.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "moderator":
                    Role modRole = roleRepository.findByName(User_Role.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(User_Role.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        }

        signUpUser.setRoles(roles);

        return ResponseEntity.ok(
                new MessageResponse("User Registered successfully", HttpStatus.ACCEPTED)

                //TODO Implement json here :) as return type
        );
        // Alternative statement: return new ResponseEntity<>("User Registered Successfully", HttpStatus.ACCEPTED);
    }
}
