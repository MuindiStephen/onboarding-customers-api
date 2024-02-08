package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.model.Role;
import com.stevemd.onboarding.model.User_Role;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.response.MessageResponse;
import com.stevemd.onboarding.response.UserDTO;
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



@RestController
public class RegisterController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
       return ResponseEntity.ok(authService.signUpUser(signUpRequest));
    }
}
