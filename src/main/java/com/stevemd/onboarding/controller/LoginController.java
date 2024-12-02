package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.wrappers.request.LoginRequest;
import com.stevemd.onboarding.wrappers.response.LoginResponse;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public LoginResponse loginUserWithCreatedAuthenticationToken(
            @RequestBody LoginRequest loginRequest
    ) {
        // Not need to autowire Authentication since obtaining it direct from Authentication Manager
        return authService.signinUser(loginRequest);
    }
}

